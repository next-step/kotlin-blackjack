package blackjack.entity

import blackjack.domain.enums.Card
import blackjack.domain.enums.CardSymbol

class BlackJack {
    val cards: MutableList<Pair<Map<CardSymbol, Card>, Boolean>> = mutableListOf()

    fun addCardCount(cardList: MutableList<Pair<MutableMap<CardSymbol, Card>, Boolean>>) {
        cards.addAll(cardList)
    }

    fun getFaceUpCards(): List<Map<CardSymbol, Card>> {
        return cards.filter { it.second }.map { it.first }
    }

    fun getTotalCardValue(): Int {
        val values =
            cards.map { (cardMap, _) ->
                cardMap.values.first().getValue()
            }
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
        return this.getTotalCardValue() >= BUST_LIMIT_VALUE
    }

    fun isLessThanSixTeen(): Boolean {
        return this.getTotalCardValue() <= DEALER_MIN_VALUE
    }

    companion object {
        const val BUST_LIMIT_VALUE = 21
        const val MINUS_ACE_VALUE = 10
        private const val DEALER_MIN_VALUE = 10
    }
}
