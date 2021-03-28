package cs.roberto.mercadolibrelite.shared.item.data.data_source.remote

import cs.roberto.mercadolibrelite.core.clean.domain.Either
import cs.roberto.mercadolibrelite.core.clean.domain.Failure
import cs.roberto.mercadolibrelite.core.exceptionktx.messageOrClassName
import cs.roberto.mercadolibrelite.shared.item.data.ItemDataSource
import cs.roberto.mercadolibrelite.shared.item.data.data_source.remote.model.dto.ItemReferenceDto
import cs.roberto.mercadolibrelite.shared.item.domain.entity.ItemReference
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references.GetItemsReferencesFailure
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references.GetItemsReferencesResponse
import cs.roberto.mercadolibrelite.shared_pool.httpclient.retrofit.retrofitApiCall
import kotlinx.coroutines.delay
import java.net.UnknownHostException

/** */
internal class ItemDataSourceRemote(
    private val itemApiService: ItemApiService,
) : ItemDataSource {

    /** */
    override suspend fun getItemsReferences(
        query: String,
        page: Int,
        pageSize: Int
    ): Either<GetItemsReferencesFailure, GetItemsReferencesResponse> =
        try {
            retrofitApiCall { itemApiService.getItemsReferences(query, page, pageSize) }.let {
                val itemsReferencesDto = it.itemsReferencesDto
                val itemsReferences = itemsReferencesDto.map(ItemReferenceDto::toItemReference)
                val response = GetItemsReferencesResponse(itemsReferences)
                Either.Right(response)
            }
        } catch (exception: Exception) {
            val failure: GetItemsReferencesFailure = when (exception) {
                is UnknownHostException -> GetItemsReferencesFailure.NetworkConnectionFailure
                else -> GetItemsReferencesFailure.DetailFailure(exception.messageOrClassName)
            }
            Either.Left(failure)
        }

}