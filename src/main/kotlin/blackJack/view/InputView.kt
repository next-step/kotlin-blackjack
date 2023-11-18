package blackJack.view

import blackJack.error.ErrorMessage

object InputView {

    fun inputNames(): String {
        val names = readln()
        require(names.isNotEmpty()) { ErrorMessage.EMPTY_INPUT.message }

        return names
    }
}
