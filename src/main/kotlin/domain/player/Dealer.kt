package domain.player

import domain.card.Card
import domain.state.State

class Dealer : Player(name = NAME, betAmount = BetAmount(DEALER_BET_AMOUNT)) {

    override fun draw(card: Card): State {
        return if (isDrawable()) {
            super.draw(card)
        } else {
            super.stop()
        }
    }

    fun isDrawable(): Boolean = cards.sum <= DRAWABLE_CARD_SUM_MAX

    companion object {
        private const val NAME = "딜러"
        private const val DEALER_BET_AMOUNT = 1
        private const val DRAWABLE_CARD_SUM_MAX = 16
    }
}
