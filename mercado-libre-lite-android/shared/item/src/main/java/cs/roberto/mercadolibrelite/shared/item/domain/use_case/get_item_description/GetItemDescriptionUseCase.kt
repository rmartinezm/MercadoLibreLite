package cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_item_description

import cs.roberto.mercadolibrelite.core.clean.domain.Either
import cs.roberto.mercadolibrelite.core.clean.domain.UseCase
import cs.roberto.mercadolibrelite.shared.item.domain.ItemRepository

/** */
class GetItemDescriptionUseCase(
    private val itemRepository: ItemRepository
) : UseCase<GetItemDescriptionResponse, GetItemDescriptionParams, GetItemDescriptionFailure>() {

    /** Execute the GetItemDescription data flow. */
    override suspend fun run(
        params: GetItemDescriptionParams
    ): Either<GetItemDescriptionFailure, GetItemDescriptionResponse> =
        itemRepository.getItemDescription(params.itemId)

}