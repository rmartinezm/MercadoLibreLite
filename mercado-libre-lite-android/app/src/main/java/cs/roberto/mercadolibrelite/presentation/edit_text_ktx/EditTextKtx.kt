package cs.roberto.mercadolibrelite.presentation.edit_text_ktx

import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText

/** */
fun EditText.onImeActionSearchClickListener(clickListener: View.OnClickListener) {
    setOnEditorActionListener { view, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            clickListener.onClick(view)
            true
        } else false
    }
}
