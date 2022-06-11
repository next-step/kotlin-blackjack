package blackjack.domain

class CustomDeck(
    cards: List<Card>
) : Deck {
    private val cards: MutableList<Card> = cards.toMutableList()

    override fun isEmpty(): Boolean {
        return cards.isEmpty()
    }

    override fun draw(): Card {
        check(cards.isNotEmpty())
        return cards.removeFirst()
    }
}
