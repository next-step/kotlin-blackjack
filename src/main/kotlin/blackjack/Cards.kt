package blackjack

class Cards(
    private val cards: List<Card>
) : List<Card> by cards {
    constructor(vararg cards: Card) : this(cards.map { it })
}
