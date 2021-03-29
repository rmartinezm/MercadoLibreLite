package cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_item_description

import cs.roberto.mercadolibrelite.core.clean.domain.Failure

/** Represents all the possibles failures associated to GetItemDescription */
sealed class GetItemDescriptionFailure : Failure() {

    /** Emitted when Internet Connection isn't available. */
    object NetworkConnectionFailure : GetItemDescriptionFailure()

    /** Emitted when the action returns an business error with detail. */
    data class DetailFailure(
        val detail: String
    ) : GetItemDescriptionFailure()

}