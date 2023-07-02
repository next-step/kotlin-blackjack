package blackjack.domain

class BlackJackGame(private val deck: Deck) {
    fun start(players: List<Player>): List<Player> {
        return players.map {
            val cards = deck.drawCards(START_CARD_COUNT)
            it.addCards(cards)
        }
    }

    companion object {
        private const val START_CARD_COUNT = 2
    }
}
