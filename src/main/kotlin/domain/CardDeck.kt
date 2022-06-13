package domain

object CardDeck {
    val allCards = Card.issueAllCards().toMutableList()

    fun pop(count: Int): MutableList<Card> {
        val cards = mutableListOf<Card>()
        repeat(count) {
            cards.add(allCards.removeLast())
        }

        return cards
    }
}
