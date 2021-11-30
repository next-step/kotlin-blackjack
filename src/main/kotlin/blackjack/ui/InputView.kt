package blackjack.ui

import blackjack.domain.ExceptionTypes.INPUT_ANSWER_FOR_MORE_CARD_MUST_NOT_NULL_OR_EMPTY
import blackjack.domain.ExceptionTypes.INPUT_ANSWER_FOR_MORE_CARD_MUST_Y_OR_N
import blackjack.domain.ExceptionTypes.INPUT_USER_NAMES_MUST_NOT_NULL_OR_EMPTY

object InputView {
    private const val ANSWER_POSITIVE = "y"
    private const val ANSWER_NEGATIVE = "n"
    private const val USER_NAME_DELIMITERS = ","

    fun readInputForPlayer(): List<String> {
        val inputString = readLine()
        require(!inputString.isNullOrEmpty()) { INPUT_USER_NAMES_MUST_NOT_NULL_OR_EMPTY }
        return inputString.split(USER_NAME_DELIMITERS)
    }

    fun readInputForMoreCard(): Boolean {
        val inputString = readLine()
        require(!inputString.isNullOrEmpty()) { INPUT_ANSWER_FOR_MORE_CARD_MUST_NOT_NULL_OR_EMPTY }
        require(ANSWER_POSITIVE == inputString || ANSWER_NEGATIVE == inputString) { INPUT_ANSWER_FOR_MORE_CARD_MUST_Y_OR_N }

        return ANSWER_POSITIVE == inputString || ANSWER_NEGATIVE != inputString
    }
}
