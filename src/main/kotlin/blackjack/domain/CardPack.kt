package blackjack.domain

class CardPack(isEmpty: Boolean = false) {
    private val cards = mutableListOf<Card>()

    init {
        if (!isEmpty) {
            createAllShapeCards()
            cards.shuffle()
        }
    }

    fun pickCard(): Card {

        check(cards.isNotEmpty()) { "모든 카드가 사용되었습니다." }

        return cards.removeAt(0)
    }

    private fun createAllShapeCards() {
        CardShape.values().forEach { cardShape ->
            createSingleShapeCardsOf(cardShape)
        }
    }

    private fun createSingleShapeCardsOf(cardShape: CardShape) {
        CardType.values().forEach { cardType ->
            cards.add(Card(cardShape, cardType))
        }
    }
}
