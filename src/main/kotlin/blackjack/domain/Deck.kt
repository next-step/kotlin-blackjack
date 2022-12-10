package blackjack.domain

object Deck {
    private val cards = Cards(Card.DECK)

    fun draw(): Card {
        return cards.pick()
    }
}
