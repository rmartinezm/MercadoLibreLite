package cs.roberto.mercadolibrelite.shared.item.data.data_source.remote

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import cs.roberto.mercadolibrelite.core.clean.domain.Either
import cs.roberto.mercadolibrelite.core.clean.domain.Failure
import cs.roberto.mercadolibrelite.core.exceptionktx.messageOrClassName
import cs.roberto.mercadolibrelite.core.stringktx.empty
import cs.roberto.mercadolibrelite.shared.item.data.ItemDataSource
import cs.roberto.mercadolibrelite.shared.item.data.data_source.remote.model.dto.ItemDescriptionDto
import cs.roberto.mercadolibrelite.shared.item.data.data_source.remote.model.dto.ItemReferenceDto
import cs.roberto.mercadolibrelite.shared.item.domain.entity.ItemReference
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_item_description.GetItemDescriptionFailure
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_item_description.GetItemDescriptionResponse
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_details.GetItemDetailsFailure
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_details.GetItemDetailsResponse
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

    /** */
    override suspend fun getItemDescription(
        itemId: String
    ): Either<GetItemDescriptionFailure, GetItemDescriptionResponse> =
        try {
            retrofitApiCall { itemApiService.getItemDescription(itemId) }.let {
                val type = Types
                    .newParameterizedType(MutableList::class.java, ItemDescriptionDto::class.java)
                val jsonAdapter: JsonAdapter<List<ItemDescriptionDto>> = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
                    .adapter(type)
                val descriptions = jsonAdapter.fromJsonValue(it) ?: listOf()
                val description: String = descriptions.firstOrNull()?.description ?: String.empty
                val response = GetItemDescriptionResponse(description)
                Either.Right(response)
            }
        } catch (exception: Exception) {
            val failure: GetItemDescriptionFailure = when (exception) {
                is UnknownHostException -> GetItemDescriptionFailure.NetworkConnectionFailure
                else -> GetItemDescriptionFailure.DetailFailure(exception.messageOrClassName)
            }
            Either.Left(failure)
        }

    /** */
    override suspend fun getItemDetails(
        itemId: String
    ): Either<GetItemDetailsFailure, GetItemDetailsResponse> =
        try {
            retrofitApiCall { itemApiService.getItemDetails(itemId) }.let {
                val itemDetails = it.toItemDetails()
                val response = GetItemDetailsResponse(itemDetails)
                Either.Right(response)
            }
        } catch (exception: Exception) {
            val failure: GetItemDetailsFailure = when (exception) {
                is UnknownHostException -> GetItemDetailsFailure.NetworkConnectionFailure
                else -> GetItemDetailsFailure.DetailFailure(exception.messageOrClassName)
            }
            Either.Left(failure)
        }

}