package domain

object CardDeck {
    private val allCards = Card.issueAllCards().toMutableList()

    fun pop(count: Int) {
        repeat(count) {
            allCards.removeLast()
        }
    }
}
