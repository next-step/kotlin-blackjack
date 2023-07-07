package blackjack.domain.user

import blackjack.domain.card.Cards

class Dealer(cards: Cards) : Player(DEALER_NAME, cards) {

    override fun checkHit(): Boolean {
        return score <= DEALER_HIT_THRESHOLD
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_HIT_THRESHOLD = 16
    }
}
