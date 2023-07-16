package blackjack.view

interface InputView {
    fun getPlayerNames(): List<String>

    fun getIsPlayerWantHit(playerName: String): Boolean
}
