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

    fun inputBettingPrice(): Int {
        val bettingPrice = readln()
        require(bettingPrice.toIntOrNull() != null) { ErrorMessage.WRONG_INPUT.message }
        return bettingPrice.toInt()
    }
}
