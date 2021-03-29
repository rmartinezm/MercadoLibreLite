package cs.roberto.mercadolibrelite.app.feature.product_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.smarteist.autoimageslider.SliderViewAdapter
import cs.roberto.mercadolibrelite.app.databinding.ViewHolderSliderImageBinding
import cs.roberto.mercadolibrelite.feature_pool.bindingktx.bindImageUrl

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