package cs.roberto.mercadolibrelite.shared.item.domain

import cs.roberto.mercadolibrelite.core.clean.domain.Either
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_item_description.GetItemDescriptionFailure
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_item_description.GetItemDescriptionResponse
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_details.GetItemDetailsFailure
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_details.GetItemDetailsResponse
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references.GetItemsReferencesFailure
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references.GetItemsReferencesResponse

/** */
interface ItemRepository {

    /** */
    suspend fun getItemsReferences(
        query: String,
        page: Int,
        pageSize: Int
    ): Either<GetItemsReferencesFailure, GetItemsReferencesResponse>

    /** */
    suspend fun getItemDescription(
        itemId: String
    ): Either<GetItemDescriptionFailure, GetItemDescriptionResponse>

    /** */
    suspend fun getItemDetails(
        itemId: String
    ): Either<GetItemDetailsFailure, GetItemDetailsResponse>

}