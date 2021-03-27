package cs.roberto.mercadolibrelite.presentation.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import cs.roberto.mercadolibrelite.R
import cs.roberto.mercadolibrelite.databinding.FragmentSearchBinding
import cs.roberto.mercadolibrelite.presentation.search.validator.SearchInputFormatError
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
        binding.mbSearchAction.setOnClickListener(::onSearchActionClickListener)
    }

    /** Action launched at click Search button */
    private fun onSearchActionClickListener(view: View) {
        if (searchViewModel.isSearchInputValid()) {
            val searchInputValue = searchViewModel.searchInput
            Toast.makeText(requireContext(), searchInputValue, Toast.LENGTH_SHORT).show()
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

}