package blackjack.view

import blackjack.domain.UserAnswer

object InputView {
    private const val NAME_DELIMITER = ","

    fun getUserNames(): List<String> {
        return readln().split(NAME_DELIMITER)
    }

    fun getYorN(): String {
        var input: String
        do {
            input = readln()
        } while (UserAnswer.isValidAnswer(input).not())

        return input
    }
}
