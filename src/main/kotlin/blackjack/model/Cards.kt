package blackjack.model

class Cards(private val cards: List<Card> = firstDraw()) : List<Card> by cards {
    companion object {
        fun firstDraw() = listOf(Card.get(), Card.get())
    }
}
