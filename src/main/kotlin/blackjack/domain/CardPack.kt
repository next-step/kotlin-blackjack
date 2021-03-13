package blackjack.domain

import blackjack.enums.CardShape
import blackjack.enums.CardType

class CardPack {
    private val cards = mutableListOf<Card>()

    init {
        CardShape.values().forEach { cardShape ->
            createCardsOf(cardShape)
        }
        cards.shuffle()
    }

    fun pickCard(): Card {

        if (cards.isEmpty()) {
            throw IllegalStateException("모든 카드가 사용되었습니다.")
        }

        val pickedCard = cards[0]
        cards.remove(pickedCard)
        return pickedCard
    }

    private fun createCardsOf(cardShape: CardShape) {
        CardType.values().forEach { cardType ->
            cards.add(Card(cardShape, cardType))
        }
    }
}
