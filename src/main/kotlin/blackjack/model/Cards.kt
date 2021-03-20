package blackjack.model

class Cards(private val cards: List<Card> = firstDraw()) : List<Card> by cards {
    override fun toString(): String {
        return cards.joinToString(separator = ", ") { "$it" }
    }

    companion object {
        fun firstDraw() = listOf(Card.get(), Card.get())
    }
}
