package cs.roberto.mercadolibrelite.app.feature.search.validator

/** */
enum class SearchInputFormatError {

    /** */
    QUERY_CANT_BE_BLANK {
        override fun isValidValue(value: String) = value.isNotBlank()
    };

    /** */
    abstract fun isValidValue(value: String): Boolean

    /** */
    companion object {

        /**
         * Validate the value received with all the possibles errors and indies if is valid.
         * @param value to validate.
         * @return [Boolean] true if is a valid value, false in other case.
         */
        fun isValidValue(value: String) = getError(value) == null

        /**
         * Obtains the first [SearchInputFormatError] that breach the value received.
         * @param value to validate
         * @return [SearchInputFormatError] if breach any function. NULL in other case.
         */
        fun getError(value: String) = values().firstOrNull { !it.isValidValue(value) }

    }

}