package cs.roberto.mercadolibrelite.shared.item.data.data_source.dummy

import cs.roberto.mercadolibrelite.core.clean.domain.Either
import cs.roberto.mercadolibrelite.shared.item.data.ItemDataSource
import cs.roberto.mercadolibrelite.shared.item.domain.entity.ItemReference
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references.GetItemsReferencesFailure
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references.GetItemsReferencesResponse
import kotlinx.coroutines.delay

/** */
internal class ItemDataSourceDummy : ItemDataSource {

    /** */
    override suspend fun getItemsReferences(
        query: String,
        page: Int,
        pageSize: Int
    ): Either<GetItemsReferencesFailure, GetItemsReferencesResponse> {
        delay(1_000)
        val itemsReferences: List<ItemReference> = listOf(
            ItemReference(
                id = "MLM738772458",
                title = " iPhone 7 32 Gb Oro",
                thumbnailUrl = "http://http2.mlstatic.com/D_782060-MLA43711395556_102020-I.jpg",
                price = 4799f,
                currency = "MXN",
                soldQuantity = 73,
                availableQuantity = 9
            )
        )
        val response = GetItemsReferencesResponse(itemsReferences)
        return Either.Left(GetItemsReferencesFailure.NetworkConnectionFailure)
    }

}