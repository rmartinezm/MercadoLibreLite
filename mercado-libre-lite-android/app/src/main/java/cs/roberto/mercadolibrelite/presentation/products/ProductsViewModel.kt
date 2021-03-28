package cs.roberto.mercadolibrelite.presentation.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import cs.roberto.mercadolibrelite.presentation.products.adapter.data_source.ItemReferenceDataSourceFactory
import cs.roberto.mercadolibrelite.shared.item.domain.entity.ItemReference
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references.GetItemsReferencesFailure
import java.util.concurrent.Executors

/** */
class ProductsViewModel(
    private val query: String,
) : ViewModel() {

    /* */
    private lateinit var dataSourceFactory: ItemReferenceDataSourceFactory

    /** */
    fun getItemReferencesPagedListAsLiveData(
        onGetItemsReferencesFailure: (GetItemsReferencesFailure) -> Unit,
        onEmptyResults: () -> Unit,
    ): LiveData<PagedList<ItemReference>> {
        dataSourceFactory = ItemReferenceDataSourceFactory(
            query,
            onGetItemsReferencesFailure,
            onEmptyResults,
        )
        val pageSize: Int = 20
        val pagedListConfig: PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(40)
            .setPageSize(pageSize)
            .build()
        return LivePagedListBuilder(dataSourceFactory, pagedListConfig)
            .setFetchExecutor(Executors.newFixedThreadPool(5))
            .build()
    }

    /** */
    fun invalidateDataSource() =
        dataSourceFactory.sourceLiveData.value?.invalidate()

}