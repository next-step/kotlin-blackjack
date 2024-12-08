package blackjack.entity

import blackjack.domain.enums.Card
import blackjack.domain.enums.CardSymbol

class BlackJack {
    val card = mutableListOf(mutableMapOf<CardSymbol, Card>())

    fun addCardCount(cards: MutableList<MutableMap<CardSymbol, Card>>) {
        card.addAll(cards)
    }

    fun getTotalCardValue(): Int {
        val values = card.flatMap { it.values.map { card -> card.getValue() } }
        var total = values.sum()
        val aceCount = values.count { it == Card.A.getValue() }
        var acesUsed = 0
        repeat(aceCount) {
            if (total > BUST_LIMIT_VALUE && acesUsed < aceCount) {
                total -= MINUS_ACE_VALUE
                acesUsed++
            }
        }
        return total
    }

    companion object {
        private const val BUST_LIMIT_VALUE = 21
        private const val MINUS_ACE_VALUE = 10
    }
}
