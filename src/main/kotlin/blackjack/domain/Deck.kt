package blackjack.domain

object Deck {
    val cards = Cards(Card.DECK)

    fun draw(): Card {
        return cards.pick()
    }
}
