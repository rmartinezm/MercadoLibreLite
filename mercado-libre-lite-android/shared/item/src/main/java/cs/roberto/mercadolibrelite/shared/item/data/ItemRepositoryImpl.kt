package cs.roberto.mercadolibrelite.shared.item.data

import cs.roberto.mercadolibrelite.core.clean.domain.Either
import cs.roberto.mercadolibrelite.shared.item.domain.ItemRepository
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

}