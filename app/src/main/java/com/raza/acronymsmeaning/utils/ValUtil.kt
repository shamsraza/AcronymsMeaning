package com.raza.acronymsmeaning.utils

object ValUtil {
    private const val SUCCESS_MESSAGE = "Success"
    private const val EMPTY_SF_MESSAGE = "Please provide valid abbreviation."
    private const val SINGLE_CHAR_SF_MESSAGE = "Abbreviation can't be single character."
    private const val NON_ALPHABET_SF_MESSAGE = "Abbreviation can contain only alphabets."
    const val NETWORK_ERROR_MESSAGE = "Please check Internet Connectivity."
    const val RESPONSE_ERROR_MESSAGE = "Response is empty."
    fun isValid(abbreviation: String): Pair<Boolean, String> {
        return if (abbreviation.isEmpty())
            Pair(false, EMPTY_SF_MESSAGE)
        else if (abbreviation.length == 1)
            Pair(false, SINGLE_CHAR_SF_MESSAGE)
        else if (!(abbreviation.matches("^[a-zA-Z]*$".toRegex())))
            Pair(false, NON_ALPHABET_SF_MESSAGE)
        else
            Pair(true, SUCCESS_MESSAGE)
    }
}