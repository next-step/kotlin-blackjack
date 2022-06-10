package blackjack

import blackjack.enums.CardPoint
import blackjack.enums.CardShape

class Card private constructor(val shape: CardShape, val point: CardPoint) {
    companion object {
        private val cardCaches: Map<CardShape, List<CardPoint>>

        init {
            cardCaches = initializeCards()
        }

        private fun initializeCards(): Map<CardShape, List<CardPoint>> {
            val cards = mutableMapOf<CardShape, List<CardPoint>>()

            for (shape in CardShape.values()) {
                cards[shape] = CardPoint.values().toList()
            }

            return cards
        }

        fun of(shape: CardShape, point: CardPoint): Card {
            val cardPoint = cardCaches[shape]?.find { it == point }
            require(cardPoint != null) { "블랙잭 카드가 아닙니다." }
            return Card(shape, cardPoint)
        }
    }
}
