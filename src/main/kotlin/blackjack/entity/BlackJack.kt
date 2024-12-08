package blackjack.entity

import blackjack.domain.enums.Card
import blackjack.domain.enums.CardSymbol

class BlackJack {
    val card = mutableMapOf<CardSymbol, Card>()

    fun addCardCount(cardMap: Map<CardSymbol, Card>) {
        cardMap.forEach { (symbol, card) -> this.card[symbol] = card }
    }

    fun getTotalCardValue(): Int {
        val values = this.card.values.map { it.getValue() }
        var total = values.sum()
        val aceCount = values.count { it == Card.A.getValue() }
        repeat(aceCount) {
            if (total > BURST_LIMIT_VALUE) {
                total -= MINUS_ACE_VALUE
            }
        }
        return total
    }

    companion object {
        private const val BURST_LIMIT_VALUE = 21
        private const val MINUS_ACE_VALUE = 10
    }
}
