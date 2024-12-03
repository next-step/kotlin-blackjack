package blackjack.entity

import blackjack.enums.Card
import blackjack.enums.CardSymbol

class BlackJack {
    val card = mutableMapOf<CardSymbol, Card>()

    fun addCardCount(cardMap: Map<CardSymbol, Card>) {
        cardMap.forEach { (symbol, card) -> this.card[symbol] = card }
    }

    fun getTotalCardValue(): Int {
        return this.card.values.sumOf { it.getValue() }
    }
}
