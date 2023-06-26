package blackjack.domain

class BlackjackGame(input: List<String>) {

    private val players = mutableListOf<Player>()

    init {
        transformToPlayers(input, makeDeck())
    }

    private fun makeDeck(): Deck = Deck.make()

    private fun transformToPlayers(
        input: List<String>,
        deck: Deck
    ) {
        val playerList = input.map {
            Player(
                it.trim(),
                listOf(deck.draw(), deck.draw())
            )
        }
        players.addAll(playerList)
    }

    fun getPlayers() = players
}
