package blackjack.domain.player

import blackjack.domain.card.Card

class Dealer(startingCards: List<Card>) : Player(DEALER_NAME, ARBITRARY_BET_AMOUNT, startingCards) {
    val shouldDrawCard: Boolean
        get() = cards.total.value <= 16

    companion object {
        const val DEALER_NAME = "딜러"
        const val ARBITRARY_BET_AMOUNT = 1
    }
}
