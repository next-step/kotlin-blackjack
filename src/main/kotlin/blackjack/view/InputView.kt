package blackjack.view

object InputView {
    fun inputPlayerNames() = readLine()!!.split(",")
        .map { it.trim() }
}
