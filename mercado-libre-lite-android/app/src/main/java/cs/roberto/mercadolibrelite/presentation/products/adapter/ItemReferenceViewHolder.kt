package cs.roberto.mercadolibrelite.presentation.products.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cs.roberto.mercadolibrelite.databinding.ViewHolderItemReferenceBinding
import cs.roberto.mercadolibrelite.shared.item.domain.entity.ItemReference

/** */
class ItemReferenceViewHolder(
    private val binding: ViewHolderItemReferenceBinding
) : RecyclerView.ViewHolder(binding.root) {

    /** */
    fun bind(itemReference: ItemReference) {
        binding.itemReference = itemReference
    }

    /** */
    companion object {

        /** */
        fun from(parent: ViewGroup): ItemReferenceViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ViewHolderItemReferenceBinding.inflate(layoutInflater, parent, false)
            return ItemReferenceViewHolder(binding)
        }

    }

}