package cs.roberto.mercadolibrelite.shared.item.presentation.get_item_details

import androidx.lifecycle.LiveData
import cs.roberto.mercadolibrelite.core.clean.domain.Either
import cs.roberto.mercadolibrelite.core.clean.presentation.Status
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_details.GetItemDetailsFailure
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_details.GetItemDetailsResponse
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references.GetItemsReferencesFailure
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references.GetItemsReferencesResponse

/** */
typealias GetItemDetailsStatus = Status<GetItemDetailsFailure, GetItemDetailsResponse>

/** */
interface GetItemDetails {

    /** */
    fun getItemDetailsAsLiveData(itemId: String): LiveData<GetItemDetailsStatus>

}