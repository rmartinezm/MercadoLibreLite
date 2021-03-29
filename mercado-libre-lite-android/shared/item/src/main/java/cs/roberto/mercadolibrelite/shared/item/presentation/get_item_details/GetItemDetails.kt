package cs.roberto.mercadolibrelite.shared.item.presentation.get_item_details

import androidx.lifecycle.LiveData
import cs.roberto.mercadolibrelite.core.clean.domain.Either
import cs.roberto.mercadolibrelite.core.clean.presentation.Status
import cs.roberto.mercadolibrelite.shared.item.domain.entity.ItemDetails
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_details.GetItemDetailsFailure
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_details.GetItemDetailsResponse
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_details.GetItemDetailsUseCase
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references.GetItemsReferencesFailure
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references.GetItemsReferencesResponse

/** [Status] related to [GetItemDetailsUseCase] data flow */
typealias GetItemDetailsStatus = Status<GetItemDetailsFailure, GetItemDetailsResponse>

/** Interface that defines the GetItemDetails interaction */
interface GetItemDetails {

    /* ItemDetails instance. */
    val itemDetails: ItemDetails?

    /** Obtains the [LiveData] flow collected related to [GetItemDetailsUseCase] */
    fun getItemDetailsAsLiveData(itemId: String): LiveData<GetItemDetailsStatus>

}