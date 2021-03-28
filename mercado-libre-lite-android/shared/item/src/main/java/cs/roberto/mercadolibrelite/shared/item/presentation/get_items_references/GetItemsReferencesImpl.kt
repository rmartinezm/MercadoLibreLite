package cs.roberto.mercadolibrelite.shared.item.presentation.get_items_references

import cs.roberto.mercadolibrelite.core.clean.domain.Either
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references.GetItemsReferencesFailure
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references.GetItemsReferencesParams
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references.GetItemsReferencesResponse
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references.GetItemsReferencesUseCase

/** */
internal class GetItemsReferencesImpl(
    private val getItemsReferencesUseCase: GetItemsReferencesUseCase
) : GetItemsReferences {

    /** */
    override suspend fun getItemsReferencesAsEither(
        query: String,
        page: Int,
        pageSize: Int
    ): Either<GetItemsReferencesFailure, GetItemsReferencesResponse> {
        val params = GetItemsReferencesParams(query, page, pageSize)
        return getItemsReferencesUseCase.run(params)
    }

}