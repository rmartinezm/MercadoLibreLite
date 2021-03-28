package cs.roberto.mercadolibrelite.presentation.binding_adapter

import android.widget.TextView
import androidx.annotation.PluralsRes
import androidx.databinding.BindingAdapter
import cs.roberto.mercadolibrelite.R
import java.text.NumberFormat
import java.util.*

@BindingAdapter("setPrice", "setCurrency", requireAll = true)
fun setPriceWithCurrency(textView: TextView, price: Float?, currency: String?) {
    if (price != null && currency != null) {
        val priceStrValue = NumberFormat.getNumberInstance(Locale.US).format(price)
        textView.text = textView.context
            .getString(R.string.item_reference_price_with_currency, priceStrValue, currency)
    }
}

@BindingAdapter("setSoldQuantity")
fun setSoldQuantity(textView: TextView, soldQuantity: Int?) {
    soldQuantity?.let {
        textView.setPluralValue(R.plurals.item_reference_sold_quantity, it)
    }
}

@BindingAdapter("setAvailableQuantity")
fun setAvailableQuantity(textView: TextView, availableQuantity: Int?) {
    availableQuantity?.let {
        textView.setPluralValue(R.plurals.item_reference_available_quantity, it)
    }
}

/** */
private fun TextView.setPluralValue(@PluralsRes resource: Int, quantity: Int) {
    text = context.resources.getQuantityString(resource, quantity, quantity)
}