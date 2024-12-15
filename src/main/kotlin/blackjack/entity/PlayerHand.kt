package blackjack.entity

import blackjack.domain.enums.Card

class PlayerHand(private val defaultCards: List<CardInfo>) {
    private var cards: List<CardInfo> = defaultCards

    fun addCards(cardInfo: List<CardInfo>) {
        this.cards.plus(cardInfo)
    }

    fun getTotalCardValue(): Int {
        val values = cards.map { it.card.values.first().getValue() }
        var total = values.sum()
        val aceCount = values.count { it == Card.A.getValue() }
        repeat(aceCount) {
            if (total > BUST_LIMIT_VALUE) {
                total -= MINUS_ACE_VALUE
            }
        }
        return total
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
