package blackjack.player

import blackjack.card.Card

const val POINT_LIMIT = 21

class PlayerCardDeck {
    private val cards = mutableListOf<Card>()
    var pointSum = 0
        private set

    companion object {
        fun of(cards: List<Card>): PlayerCardDeck {
            val deck = PlayerCardDeck()
            cards.forEach { deck.add(it) }

            return deck
        }

        fun of(pointSum: Int): PlayerCardDeck {
            val deck = PlayerCardDeck()
            deck.pointSum = pointSum
            return deck
        }
    }

    fun add(card: Card) {
        this.pointSum += this.getPoint(card)
        this.cards.add(card)
    }

    private fun getPoint(card: Card): Int {
        if (pointSum + card.primaryPoint <= POINT_LIMIT) {
            return card.primaryPoint
        }

        if (pointSum + card.secondaryPoint <= POINT_LIMIT) {
            return card.secondaryPoint
        }

        throw IllegalArgumentException("$POINT_LIMIT 을 초과한 카드를 뽑았습니다. card=${card.fullName()}")
    }
}
