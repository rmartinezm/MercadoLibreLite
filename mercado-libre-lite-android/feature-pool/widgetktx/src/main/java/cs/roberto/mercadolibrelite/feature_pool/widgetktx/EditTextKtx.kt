package cs.roberto.mercadolibrelite.feature_pool.widgetktx

import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText

/** Setup the click listener at ImeActionSearch action */
fun EditText.onImeActionSearchClickListener(clickListener: View.OnClickListener) {
    setOnEditorActionListener { view, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            clickListener.onClick(view)
            true
        } else false
    }
}
