package cs.roberto.mercadolibrelite.shared.item.data

import cs.roberto.mercadolibrelite.core.clean.domain.Either
import cs.roberto.mercadolibrelite.shared.item.domain.ItemRepository
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_item_description.GetItemDescriptionFailure
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_item_description.GetItemDescriptionResponse
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_details.GetItemDetailsFailure
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_details.GetItemDetailsResponse
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references.GetItemsReferencesFailure
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references.GetItemsReferencesResponse

/** */
internal class ItemRepositoryImpl(
    private val itemDataSource: ItemDataSource
) : ItemRepository {

    /** */
    override suspend fun getItemsReferences(
        query: String,
        page: Int,
        pageSize: Int
    ): Either<GetItemsReferencesFailure, GetItemsReferencesResponse> =
        itemDataSource.getItemsReferences(query, page, pageSize)

    /** */
    override suspend fun getItemDescription(
        itemId: String
    ): Either<GetItemDescriptionFailure, GetItemDescriptionResponse> =
        itemDataSource.getItemDescription(itemId)

    /** */
    override suspend fun getItemDetails(
        itemId: String
    ): Either<GetItemDetailsFailure, GetItemDetailsResponse> =
        itemDataSource.getItemDetails(itemId)

}