package blackjack.entity

import blackjack.domain.enums.Card

class PlayerHand(private val defaultCards: List<CardInfo>) {
    private var cards: List<CardInfo> = defaultCards

    fun addCards(cardInfo: List<CardInfo>) {
        this.cards.plus(cardInfo)
    }

    fun getTotalCardValue(): Int {
        val values = cards.map { it.card.values.first().getValue() }

        return values.fold(0) { total, value ->
            val newTotal = total + value
            cardBustFromA(value, newTotal)
        }
    }

    private fun cardBustFromA(value: Int, newTotal: Int) =
        if (value == Card.A.getValue() && newTotal > BUST_LIMIT_VALUE) {
            newTotal - MINUS_ACE_VALUE
        } else {
            newTotal
        }

    fun isBust(): Boolean {
        return getTotalCardValue() > BUST_LIMIT_VALUE
    }

    fun isLessThanSixteen(): Boolean {
        return getTotalCardValue() < DEALER_MIN_VALUE
    }

    fun showCards(): List<String> {
        return cards.map { cardInfo ->
            val card = cardInfo.card.entries.first()
            if (cardInfo.isFaceUp) "${card.key.getType()}${card.value.getType()}" else "Hidden"
        }
    }

    companion object {
        const val BUST_LIMIT_VALUE = 21
        const val MINUS_ACE_VALUE = 10
        const val DEALER_MIN_VALUE = 16
    }
}
