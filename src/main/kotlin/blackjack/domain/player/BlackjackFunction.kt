package blackjack.domain.player

import blackjack.domain.card.Card

interface BlackjackFunction {
    fun addCard(card: Card)
    fun getCardSum(): Int
    fun isOverDeadline(): Boolean
    fun isBlackjack(): Boolean
    fun addRevenue(revenue: Double)
    fun minusRevenue(loss: Double)
    fun getRevenue(): Double
}
