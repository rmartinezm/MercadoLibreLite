package cs.roberto.mercadolibrelite.feature_pool.widgetktx

import android.widget.TextView
import androidx.annotation.PluralsRes

/** Setup the plural text value into TextView */
fun TextView.setPluralValue(@PluralsRes resource: Int, quantity: Int) {
    text = context.resources.getQuantityString(resource, quantity, quantity)
}