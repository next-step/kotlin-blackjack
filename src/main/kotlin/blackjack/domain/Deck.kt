package blackjack.domain

class Deck {
    lateinit var cards: MutableList<Card>

    fun draw(): Card {
        return cards.removeFirst()
    }

    fun shuffle() {
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
