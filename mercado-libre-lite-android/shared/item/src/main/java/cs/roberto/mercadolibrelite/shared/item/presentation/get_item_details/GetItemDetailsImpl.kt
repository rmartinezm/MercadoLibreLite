package cs.roberto.mercadolibrelite.shared.item.presentation.get_item_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import cs.roberto.mercadolibrelite.core.clean.domain.onLeft
import cs.roberto.mercadolibrelite.core.clean.domain.onRight
import cs.roberto.mercadolibrelite.core.clean.presentation.Status
import cs.roberto.mercadolibrelite.shared.item.domain.entity.ItemDetails
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_details.GetItemDetailsParams
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_details.GetItemDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow

/** Implements the GetItemDetails data flow interaction */
internal class GetItemDetailsImpl(
    private val getItemDetailsUseCase: GetItemDetailsUseCase
) : GetItemDetails {

    /* ItemDetails instance. */
    override var itemDetails: ItemDetails? = null

    /** Obtains the [LiveData] flow collected related to [GetItemDetailsUseCase] */
    override fun getItemDetailsAsLiveData(
        itemId: String
    ): LiveData<GetItemDetailsStatus> = flow<GetItemDetailsStatus> {
        emit(Status.Loading())
        val params = GetItemDetailsParams(itemId)
        getItemDetailsUseCase.run(params)
            .onLeft { emit(Status.Failed(it)) }
            .onRight {
                itemDetails = it.itemDetails
                emit(Status.Done(it))
            }
    }.asLiveData(Dispatchers.IO)

}