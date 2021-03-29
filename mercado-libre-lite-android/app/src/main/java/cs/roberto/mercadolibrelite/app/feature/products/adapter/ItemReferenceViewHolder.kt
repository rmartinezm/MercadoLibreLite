package cs.roberto.mercadolibrelite.app.feature.products.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cs.roberto.mercadolibrelite.app.databinding.ViewHolderItemReferenceBinding
import cs.roberto.mercadolibrelite.shared.item.domain.entity.ItemReference

/** */
class ItemReferenceViewHolder(
    private val binding: ViewHolderItemReferenceBinding
) : RecyclerView.ViewHolder(binding.root) {

    /* */
    private lateinit var itemReference: ItemReference

    /** */
    fun bind(itemReference: ItemReference) {
        this.itemReference = itemReference
        binding.itemReference = this.itemReference
    }

    /** */
    fun setOnItemReferenceActionClickListener(actionListener: (ItemReference) -> Unit) {
        binding.root.setOnClickListener {
            actionListener(itemReference)
        }
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