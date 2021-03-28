package cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_details

import cs.roberto.mercadolibrelite.core.clean.domain.*
import cs.roberto.mercadolibrelite.core.stringktx.empty
import cs.roberto.mercadolibrelite.shared.item.domain.ItemRepository
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_item_description.GetItemDescriptionFailure
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_item_description.GetItemDescriptionParams
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_item_description.GetItemDescriptionResponse
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_item_description.GetItemDescriptionUseCase

/** */
class GetItemDetailsUseCase(
    private val itemRepository: ItemRepository,
    private val getItemDescriptionUseCase: GetItemDescriptionUseCase,
) : UseCase<GetItemDetailsResponse, GetItemDetailsParams, GetItemDetailsFailure>() {

    /** */
    override suspend fun run(
        params: GetItemDetailsParams
    ): Either<GetItemDetailsFailure, GetItemDetailsResponse> {
        val response = itemRepository.getItemDetails(params.itemId)
        return response
            .flatMapSuspend(::executeGetItemDescription)
            .flatMap {
                transformationGetItemDescriptionToGetItemDetails(
                    getItemDetailsResponse = response.rightValue(),
                    getItemDescriptionResponse = it,
                )
            }
            .composeFailure(::composeFailure)
    }

    /** */
    private suspend fun executeGetItemDescription(
        getItemDetailsResponse: GetItemDetailsResponse
    ): Either<GetItemDescriptionFailure, GetItemDescriptionResponse> {
        val itemId: String = getItemDetailsResponse.itemDetails.id
        val params = GetItemDescriptionParams(itemId)
        return getItemDescriptionUseCase.run(params)
            .manageLeftToEither { Either.Right(GetItemDescriptionResponse(String.empty)) }
    }

    /** */
    private fun transformationGetItemDescriptionToGetItemDetails(
        getItemDetailsResponse: GetItemDetailsResponse,
        getItemDescriptionResponse: GetItemDescriptionResponse
    ): Either<GetItemDetailsFailure, GetItemDetailsResponse> {
        val itemDetails = getItemDetailsResponse.itemDetails.apply {
            description = getItemDescriptionResponse.description
        }
        val response = GetItemDetailsResponse(itemDetails)
        return Either.Right(response)
    }

    /** */
    private fun composeFailure(failure: Failure): GetItemDetailsFailure =
        when (failure) {
            is GetItemDetailsFailure -> failure
            is GetItemDescriptionFailure -> when (failure) {
                GetItemDescriptionFailure.NetworkConnectionFailure ->
                    GetItemDetailsFailure.NetworkConnectionFailure
                is GetItemDescriptionFailure.DetailFailure ->
                    GetItemDetailsFailure.DetailFailure(failure.detail)
            }
            else -> GetItemDetailsFailure.DetailFailure(failure.javaClass.simpleName)
        }

}