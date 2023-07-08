package domain.player

import domain.card.Cards

class Dealer(cards: Cards = Cards()) : Player("딜러", cards) {
    override fun canReceiveMoreCard(): Boolean {
        return result() <= DEALER_MAX_POINT
    }

    companion object {
        const val DEALER_MAX_POINT = 16
    }
}
