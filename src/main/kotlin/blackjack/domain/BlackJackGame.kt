package blackjack.domain

class BlackJackGame(private val deck: Deck) {
    fun start(players: List<Player>): List<Player> {
        return players.map {
            val cards = deck.drawCards(START_CARD_COUNT)
            it.drawCards(cards)
        }
    }

    fun addCard(player: Player): Player {
        val card = deck.drawCard()
        return player.drawCard(card)
    }

    companion object {
        private const val START_CARD_COUNT = 2

        fun create() = BlackJackGame(Deck.create())
    }
}
