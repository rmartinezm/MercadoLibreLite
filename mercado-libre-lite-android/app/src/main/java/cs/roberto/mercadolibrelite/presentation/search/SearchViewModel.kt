package cs.roberto.mercadolibrelite.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cs.roberto.mercadolibrelite.core.stringktx.empty
import cs.roberto.mercadolibrelite.presentation.search.validator.SearchInputFormatError
import kotlin.jvm.Throws

/** */
class SearchViewModel : ViewModel() {

    /* */
    val searchInputLiveData: MutableLiveData<String> = MutableLiveData()
    val searchInput: String get() = searchInputLiveData.value ?: String.empty

    /**
     * Validate the value received with all the possibles errors and indies if is valid.
     * @return [Boolean] true if is a valid value, false in other case.
     */
    fun isSearchInputValid(): Boolean =
        SearchInputFormatError.isValidValue(searchInput)

    /**
     * Obtains the first [SearchInputFormatError] that breach the value received.
     * @return [SearchInputFormatError] if breach any function.
     * @throws [NullPointerException] if don't exists format error that applies.
     */
    @Throws(NullPointerException::class)
    fun getSearchInputFormatError(): SearchInputFormatError =
        SearchInputFormatError.getError(searchInput)!!

}