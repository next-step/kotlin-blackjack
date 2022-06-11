package blackjack.view.input

interface Input {
    fun readPlayers(): String

    fun readHasNextCard(): String
}
