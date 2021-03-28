package blackjack

class Deck(cards: List<Card>) {
    private val cards = cards.toMutableList()

    fun draw(): Card {
        return try {
            cards.removeAt(0)
        } catch (e: IndexOutOfBoundsException) {
            throw NoSuchElementException()
        }
    }

    companion object {
        fun newDeck(): Deck = Deck(Card.ALL)
    }
}
