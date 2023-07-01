package blackjack.view

interface InputView {
    fun getPlayerNames(): List<String>

    fun getPlayerHitStatus(playerName: String): String
}
