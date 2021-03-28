package cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references

import cs.roberto.mercadolibrelite.core.clean.domain.Either
import cs.roberto.mercadolibrelite.core.clean.domain.UseCase
import cs.roberto.mercadolibrelite.shared.item.domain.ItemRepository

/** */
class GetItemsReferencesUseCase(
    private val itemRepository: ItemRepository
) : UseCase<GetItemsReferencesResponse, GetItemsReferencesParams, GetItemsReferencesFailure>() {

    /** */
    override suspend fun run(
        params: GetItemsReferencesParams
    ): Either<GetItemsReferencesFailure, GetItemsReferencesResponse> =
        itemRepository.getItemsReferences(params.query, params.page, params.pageSize)

}