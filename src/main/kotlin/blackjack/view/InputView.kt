package blackjack.view

object InputView {
    fun getInputPlayers() {
        runCatching {
            readln().split(",")
        }
    }
}