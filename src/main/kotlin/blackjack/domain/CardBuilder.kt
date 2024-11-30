package blackjack.domain

class CardBuilder {
    private val cards: MutableList<Card> = mutableListOf()

    infix fun String.to(suit: Suit) {
        cards.add(Card(this, suit))
    }

    fun build(): List<Card> {
        return cards
    }
}
