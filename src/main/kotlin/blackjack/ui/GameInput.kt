package blackjack.ui

interface GameInput {
    fun requestPlayers(): List<String>

    fun requestConfirmDrawCard(): String
}
