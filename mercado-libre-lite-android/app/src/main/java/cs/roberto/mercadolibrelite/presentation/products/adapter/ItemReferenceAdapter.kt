package cs.roberto.mercadolibrelite.presentation.products.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import cs.roberto.mercadolibrelite.shared.item.domain.entity.ItemReference

/** */
class ItemReferenceAdapter : PagedListAdapter<ItemReference, ItemReferenceViewHolder>(ItemReferenceDiffCallback) {

    /** */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemReferenceViewHolder =
        ItemReferenceViewHolder.from(parent)

    /** */
    override fun onBindViewHolder(holder: ItemReferenceViewHolder, position: Int) {
        val itemReference: ItemReference? = getItem(position)
        itemReference?.let { holder.bind(it) }
    }

    /** */
    private object ItemReferenceDiffCallback : DiffUtil.ItemCallback<ItemReference>() {

        /** */
        override fun areItemsTheSame(
            oldItem: ItemReference,
            newItem: ItemReference
        ): Boolean = oldItem.id == newItem.id

        /** */
        override fun areContentsTheSame(
            oldItem: ItemReference,
            newItem: ItemReference
        ): Boolean = oldItem.equals(newItem)

    }

}