package cs.roberto.mercadolibrelite.app.feature.search

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import cs.roberto.mercadolibrelite.app.R
import cs.roberto.mercadolibrelite.app.databinding.FragmentSearchBinding
import cs.roberto.mercadolibrelite.app.feature.search.validator.SearchInputFormatError
import cs.roberto.mercadolibrelite.feature_pool.widgetktx.onImeActionSearchClickListener
import org.koin.android.viewmodel.ext.android.viewModel

/** */
class SearchFragment : Fragment() {

    /* ViewDataBinding instance associated to the Fragment */
    private val binding: FragmentSearchBinding by lazy {
        FragmentSearchBinding.inflate(layoutInflater).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = searchViewModel
        }
    }

    /* ViewModel instance associated to the Fragment */
    private val searchViewModel: SearchViewModel by viewModel()

    /** */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    /** */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    /** Manage the Views init or fill actions */
    private fun setupViews() {
        setupTextChangeListener()
        setupActions()
    }

    /** Manage the TextChangeListeners association to views */
    private fun setupTextChangeListener() {
        binding.apply {
            tietSearchInput.setClearErrorListenerOnType(tilSearchInput)
        }
    }

    /** Manage the actions associate for views */
    private fun setupActions() {
        binding.apply {
            tietSearchInput.onImeActionSearchClickListener(::onSearchActionClickListener)
            mbSearchAction.setOnClickListener(::onSearchActionClickListener)
        }
    }

    /** Action launched at click Search button */
    private fun onSearchActionClickListener(view: View) {
        if (searchViewModel.isSearchInputValid()) {
            val searchInputValue = searchViewModel.searchInput
            val direction =
                SearchFragmentDirections.actionSearchFragmentToProductsFragment(searchInputValue)
            findNavController().navigate(direction)
            hideKeyboard(view)
        } else manageSearchInputFormatError(searchViewModel.getSearchInputFormatError())
    }

    /** Manage the [formatError] display mode */
    private fun manageSearchInputFormatError(formatError: SearchInputFormatError) {
        val errorMessage: String = when (formatError) {
            SearchInputFormatError.QUERY_CANT_BE_BLANK ->
                getString(R.string.search_screen_search_input_failure_query_cant_be_blank)
        }
        binding.tilSearchInput.error = errorMessage
    }

    /**
     * Add textChangedListener to the [TextInputEditText] to clear the error form the
     * [textInputLayout] at text changed.
     */
    private fun TextInputEditText.setClearErrorListenerOnType(textInputLayout: TextInputLayout) {
        addTextChangedListener { textInputLayout.error = null }
    }

    /** */
    private fun hideKeyboard(view: View) {
        val inputMethodManager = requireContext()
            .getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}