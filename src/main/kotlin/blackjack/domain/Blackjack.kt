package blackjack.domain

class Blackjack(
    val players: List<Player>
) {
    private val deck = Deck.create()

    init {
        deck.shuffle()
    }

    fun drawingCard(player: Player): Card {
        val card = deck.draw()
        player.addCard(deck.draw())

        return card
    }

    fun drawingCards(player: Player, n: Int): List<Card> {
        val cards = deck.draw(n)
        player.addCards(cards)

        return cards
    }
}
