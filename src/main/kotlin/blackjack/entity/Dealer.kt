package blackjack.entity

import blackjack.entity.PlayerHand.Companion.BUST_LIMIT_VALUE

class Dealer(val name: String, defaultCards: List<CardInfo>) {
    val hand: PlayerHand = PlayerHand(defaultCards)

    fun isBlackJack(): Boolean = this.hand.getTotalCardValue() == BUST_LIMIT_VALUE
}
