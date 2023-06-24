package blackjack.model

class BlackjackPlayers(
    deck: CardDeck,
    blackjackPlayersCardCountConsumer: BlackjackPlayersCardCountConsumer,
    val players: Collection<BlackjackPlayer>,
) {
    init {
        repeat(INITIAL_DEALING_COUNT) {
            players.map { it.add(deck.draw()) }
        }
        blackjackPlayersCardCountConsumer.consumePlayersCardCount(this, INITIAL_DEALING_COUNT)
    }

    fun forEach(action: (BlackjackPlayer) -> Unit) {
        players.forEach(action)
    }

    companion object {
        private const val INITIAL_DEALING_COUNT: Int = 2
    }
}
