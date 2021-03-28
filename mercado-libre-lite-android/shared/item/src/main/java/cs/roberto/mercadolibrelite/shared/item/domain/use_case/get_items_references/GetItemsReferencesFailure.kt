package cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references

import cs.roberto.mercadolibrelite.core.clean.domain.Failure

/** Represents all the possibles failures associated to GetItems use case */
sealed class GetItemsReferencesFailure : Failure() {

    /** Emitted when Internet Connection isn't available. */
    object NetworkConnectionFailure : GetItemsReferencesFailure()

    /** Emitted when the action returns an business error with detail. */
    data class DetailFailure(
        val detail: String
    ) : GetItemsReferencesFailure()

}