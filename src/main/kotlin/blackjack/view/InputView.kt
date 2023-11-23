package blackjack.view

interface InputView {
    fun getPlayerNames(): List<String>

    fun getPlayerCommand(playerName: String): String
}
