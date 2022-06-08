package blackjack.view

import blackjack.domain.InputValidator

object InputView {
    private const val INVALID_YES_OR_NO = "y나 n만 입력해주세요"
    private const val NAME_DELIMITER = ","

    fun getUserNames(): List<String> {
        return readln().split(NAME_DELIMITER)
    }

    fun getYorN(): String {
        var input: String
        do {
            input = readln()
        } while (isValidAnswer(input).not())

        return input
    }

    private fun isValidAnswer(input: String): Boolean {
        return try {
            InputValidator.checkYorN(input)
            true
        } catch (e: IllegalArgumentException) {
            println(INVALID_YES_OR_NO)
            false
        }
    }
}
