package cs.roberto.mercadolibrelite.feature_pool.bindingktx

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visibilityByBoolean")
fun visibilityByBoolean(view: View, isVisible: Boolean?) {
    view.visibility = if (isVisible == true)
        View.VISIBLE
    else View.GONE
}