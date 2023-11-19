package blackJack.view

import blackJack.error.ErrorMessage

object InputView {

    fun inputNames(): String {
        val names = readln()
        require(names.isNotEmpty()) { ErrorMessage.EMPTY_INPUT.message }

        return names
    }

    fun answerYesOrNo(): String {
        val answer = readln()
        require(answer == "y" || answer == "n") { ErrorMessage.WRONG_INPUT.message }
        return answer
    }
}
