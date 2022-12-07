package blackjack

class Deck {
    lateinit var cards: MutableList<Card>

    init {
        shuffle()
    }

    fun draw(): Card {
        return cards.removeFirst()
    }

    private fun shuffle() {
        cards = Shape.values()
            .asSequence()
            .map { getCardsByShape(it) }
            .reduce { acc, cards -> acc.plus(cards) }
            .shuffled()
            .toMutableList()
    }

    private fun getCardsByShape(shape: Shape): List<Card> {
        return Denomination.values()
            .asSequence()
            .map { Card(shape, it) }
            .toMutableList()
    }
}
