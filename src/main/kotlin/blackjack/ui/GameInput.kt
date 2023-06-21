package blackjack.ui

interface GameInput {
    fun requestPlayers(): List<String>

    fun requestConfirmDrawCard(name: String): String
}
