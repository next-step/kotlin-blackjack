package domain

object CardDeck {
    private val allCards = Card.issueAllCards().toMutableList()

    fun pop(count: Int): MutableList<Card> {
        val cards = mutableListOf<Card>()
        repeat(count) {
            cards.add(allCards.removeLast())
        }

        return cards
    }
}
