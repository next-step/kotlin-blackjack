package blackjack.domain.player

import blackjack.domain.card.Card

class Dealer(startingCards: List<Card>) : Player(DEALER_NAME, startingCards) {
    val shouldDrawCard: Boolean
        get() = cards.total.value <= 16

    companion object {
        const val DEALER_NAME = "딜러"
    }
}
