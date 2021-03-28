package cs.roberto.mercadolibrelite.shared.item.data.data_source.dummy

import cs.roberto.mercadolibrelite.core.clean.domain.Either
import cs.roberto.mercadolibrelite.core.stringktx.empty
import cs.roberto.mercadolibrelite.shared.item.data.ItemDataSource
import cs.roberto.mercadolibrelite.shared.item.domain.entity.ItemDetails
import cs.roberto.mercadolibrelite.shared.item.domain.entity.ItemReference
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_item_description.GetItemDescriptionFailure
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_item_description.GetItemDescriptionResponse
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_details.GetItemDetailsFailure
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_details.GetItemDetailsResponse
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
        return Either.Right(response)
    }

    /** */
    override suspend fun getItemDescription(
        itemId: String
    ): Either<GetItemDescriptionFailure, GetItemDescriptionResponse> {
        delay(300)
        val description = "My lorem description"
        val response = GetItemDescriptionResponse(description)
        return Either.Right(response)
    }

    /** */
    override suspend fun getItemDetails(
        itemId: String
    ): Either<GetItemDetailsFailure, GetItemDetailsResponse> {
        delay(1_000)
        val itemDetails: ItemDetails = ItemDetails(
            id = "MLM738772458",
            title = " iPhone 7 32 Gb Oro",
            thumbnailUrl = "http://http2.mlstatic.com/D_782060-MLA43711395556_102020-I.jpg",
            price = 4799f,
            currency = "MXN",
            soldQuantity = 73,
            availableQuantity = 9,
            permalink = "https://www.mercadolibre.com.mx/laptop-huawei-matebook-d15-space-gray-156-amd-ryzen-7-3700u-8gb-de-ram-512gb-ssd-amd-radeon-vega-10-1920x1080px-windows-10-home/p/MLM16380439",
            acceptsMercadoPago = true,
            description = String.empty,
            picturesUrls = listOf(
                "https://http2.mlstatic.com/D_782060-MLA43711395556_102020-O.jpg",
                "https://http2.mlstatic.com/D_679092-MLA43711395557_102020-O.jpg"
            )
        )
        val response = GetItemDetailsResponse(itemDetails)
        return Either.Right(response)
    }

}