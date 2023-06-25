package blackjack.ui

interface GameInput {
    fun requestPlayers(): List<Pair<String, Double>>

    fun requestPlayerNames(): List<String>

    fun requestPlayerBet(name: String): Double

    fun requestConfirmDrawCard(name: String): String
}
