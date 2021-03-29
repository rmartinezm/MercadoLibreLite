package cs.roberto.mercadolibrelite.presentation.product_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.smarteist.autoimageslider.SliderViewAdapter
import cs.roberto.mercadolibrelite.databinding.ViewHolderSliderImageBinding
import cs.roberto.mercadolibrelite.presentation.binding_adapter.bindImageUrl

/** */
class SliderImageViewHolder(
    private val binding: ViewHolderSliderImageBinding
) : SliderViewAdapter.ViewHolder(binding.root) {

    /** */
    fun bindUrl(url: String) {
        bindImageUrl(binding.ivImage, url)
    }

    /** */
    companion object {

        /** */
        fun from(parent: ViewGroup): SliderImageViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ViewHolderSliderImageBinding.inflate(layoutInflater, parent, false)
            return SliderImageViewHolder(binding)
        }

    }

}