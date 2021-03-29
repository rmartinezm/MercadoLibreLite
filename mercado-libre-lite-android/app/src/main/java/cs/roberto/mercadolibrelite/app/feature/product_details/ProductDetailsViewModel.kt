package cs.roberto.mercadolibrelite.app.feature.product_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import cs.roberto.mercadolibrelite.shared.item.presentation.get_item_details.GetItemDetails
import cs.roberto.mercadolibrelite.shared.item.presentation.get_item_details.GetItemDetailsStatus

/** */
class ProductDetailsViewModel(
    private val itemId: String,
    getItemDetails: GetItemDetails,
) : ViewModel(),
    GetItemDetails by getItemDetails {

    /** */
    fun getItemDetailsAsLiveData(): LiveData<GetItemDetailsStatus> =
        getItemDetailsAsLiveData(itemId)

}