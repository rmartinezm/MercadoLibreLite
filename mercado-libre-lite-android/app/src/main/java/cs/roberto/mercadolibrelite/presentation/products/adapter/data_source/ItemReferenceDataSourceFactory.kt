package cs.roberto.mercadolibrelite.presentation.products.adapter.data_source

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import cs.roberto.mercadolibrelite.shared.item.domain.entity.ItemReference
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references.GetItemsReferencesFailure

/** */
class ItemReferenceDataSourceFactory(
    private val query: String,
    private val onGetItemsReferencesFailure: (GetItemsReferencesFailure) -> Unit,
    private val onEmptyResults: () -> Unit,
) : DataSource.Factory<Int, ItemReference>() {

    /* */
    val sourceLiveData = MutableLiveData<ItemReferencePageDataSource>()

    /** */
    override fun create(): DataSource<Int, ItemReference> {
        val source = ItemReferencePageDataSource(query, onGetItemsReferencesFailure, onEmptyResults)
        sourceLiveData.postValue(source)
        return source
    }

}