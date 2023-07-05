package blackjack.domain

class CardDeck {
    private val cards = Card.allCards.toMutableList()

    fun size(): Int = cards.size
}
