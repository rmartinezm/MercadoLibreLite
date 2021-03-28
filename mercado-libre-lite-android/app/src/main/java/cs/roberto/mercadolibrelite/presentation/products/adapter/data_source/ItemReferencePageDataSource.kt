package cs.roberto.mercadolibrelite.presentation.products.adapter.data_source

import androidx.paging.PageKeyedDataSource
import cs.roberto.mercadolibrelite.core.clean.domain.onLeft
import cs.roberto.mercadolibrelite.core.clean.domain.onRight
import cs.roberto.mercadolibrelite.shared.item.domain.entity.ItemReference
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references.GetItemsReferencesFailure
import cs.roberto.mercadolibrelite.shared.item.presentation.get_items_references.GetItemsReferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

/** */
class ItemReferencePageDataSource(
    private val query: String,
    private val onGetItemsReferencesFailure: (GetItemsReferencesFailure) -> Unit,
    private val onEmptyResults: () -> Unit,
) : PageKeyedDataSource<Int, ItemReference>() {

    /* */
    private val getItemsReferences: GetItemsReferences by inject(GetItemsReferences::class.java)

    /** */
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ItemReference>
    ) {
        val previousPageKey: Int? = null
        CoroutineScope(Dispatchers.IO).launch {
            getItemsReferences.getItemsReferencesAsEither(query, 1, params.requestedLoadSize)
                .onLeft {
                    onGetItemsReferencesFailure(it)
                    callback.onResult(mutableListOf(), previousPageKey, null)
                }
                .onRight {
                    if (it.itemsReferences.isEmpty()) {
                        onEmptyResults()
                        callback.onResult(mutableListOf(), previousPageKey, null)
                    } else callback.onResult(it.itemsReferences, previousPageKey, 2)
                }
        }
    }

    /** */
    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ItemReference>
    ) {
        /* NOT IMPLEMENTATION REQUIRED */
    }

    /** */
    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ItemReference>
    ) {
        val page: Int = params.key
        CoroutineScope(Dispatchers.IO).launch {
            getItemsReferences.getItemsReferencesAsEither(query, page, params.requestedLoadSize)
                .onLeft { callback.onResult(mutableListOf(), null) }
                .onRight { callback.onResult(it.itemsReferences, page.inc()) }
        }
    }

}