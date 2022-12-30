package blackjack.domain

object Draw {
    const val FIRST_DRAW_COUNT = 2
    const val DRAW_LIMIT = 21

    fun checkDrawable(drawYn: String): Boolean = (drawYn == "y")

    fun draw(): Card {
        var card: Card
        do {
            card = Card()
        } while (Deck.isDrawn(card))
        Deck.draw(card)
        return card
    }
}
