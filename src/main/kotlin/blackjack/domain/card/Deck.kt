package blackjack.domain.card

class Deck {
    private val cards: MutableList<Card> by lazy {
        makeCards()
    }

    fun draw(): Card {
        check(cards.isNotEmpty()) {
            "카드를 전부 뽑았습니다."
        }

        return cards.removeFirst()
    }

    fun shuffle() {
        cards.shuffle()
    }

    private fun makeCards() = Shape.values()
        .map { getCardsByShape(it) }
        .reduce { acc, cards -> acc.plus(cards) }
        .toMutableList()

    private fun getCardsByShape(shape: Shape): List<Card> {
        return Denomination.values()
            .map { Card(shape, it) }
            .toMutableList()
    }
}
