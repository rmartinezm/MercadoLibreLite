package cs.roberto.mercadolibrelite.feature_pool.bindingktx

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/** Load the [imageUrl] received as parameter into [imageView] powered by [Glide] */
@BindingAdapter("bindImageUrl")
fun bindImageUrl(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(imageView)
            .load(it)
            .into(imageView)
    }
}
