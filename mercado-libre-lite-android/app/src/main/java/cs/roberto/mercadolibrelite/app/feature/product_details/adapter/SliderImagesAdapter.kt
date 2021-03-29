package cs.roberto.mercadolibrelite.app.feature.product_details.adapter

import android.view.ViewGroup
import com.smarteist.autoimageslider.SliderViewAdapter

/** */
class SliderImagesAdapter(
    private val imagesUrls: List<String>,
) : SliderViewAdapter<SliderImageViewHolder>() {

    /** */
    override fun getCount(): Int = imagesUrls.size

    /** */
    override fun onCreateViewHolder(parent: ViewGroup): SliderImageViewHolder =
        SliderImageViewHolder.from(parent)

    /** */
    override fun onBindViewHolder(holder: SliderImageViewHolder, position: Int) {
        val imageUrl: String = imagesUrls[position]
        holder.bindUrl(imageUrl)
    }

}