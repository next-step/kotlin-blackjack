package com.nextstep.blackjack.view

object InputView {
    const val CONTINUE = "y"
    const val STOP = "n"

    fun inputMessage(): String {
        return readln()
    }

    fun inputMessageSplitWithComma(): List<String> {
        return readln().split(",")
    }
}
