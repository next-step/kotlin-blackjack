package blackjack.domain

class BlackJackGame(private val deck: Deck) {
    fun start(players: Players): Players {
        return players.addCards { deck.drawCards(START_CARD_COUNT) }
    }

    fun addCard(player: Player): Player {
        val card = deck.drawCard()
        return player.addCard(card)
    }

    companion object {
        private const val START_CARD_COUNT = 2

        fun create() = BlackJackGame(Deck.create())
    }
}
