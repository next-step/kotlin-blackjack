package blackjack

class Deck(cards: List<Card>) {
    val cards = cards.toMutableList()

    fun draw(): Card {
        return try {
            cards.removeAt(0)
        } catch (e: IndexOutOfBoundsException) {
            throw NoSuchElementException()
        }
    }
}
