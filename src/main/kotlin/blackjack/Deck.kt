package blackjack

class Deck {
    private val cards = CardSuit.entries.flatMap {
        suit -> CardNumber.entries.map { number -> Card(suit, number) }
    }
        .shuffled()
        .toMutableList()

    fun draw(): Card = cards.removeAt(0)
}
