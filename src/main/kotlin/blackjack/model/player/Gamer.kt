package blackjack.model.player

import blackjack.model.Money
import blackjack.model.card.PlayerCards
import blackjack.view.InputView

class Gamer(override val name: String) : Player {
    override var cards = PlayerCards()
    override var money = Money()

    override fun call(): Boolean {
        while (continueToTurn() && InputView.askToDraw(this)) {
            return true
        }
        return false
    }

    fun getPrizeRate(dealer: Dealer): Double {
        if (dealer.cards.isBust()) {
            return DOUBLE
        }

        if (cards.isBlackJack()) {
            return if (dealer.cards.isBlackJack()) {
                RESTORE
            } else {
                DOUBLE_HALF
            }
        }

        if (isGamerWin(dealer.cards)) {
            return DOUBLE
        }
        return ZERO
    }

    private fun isGamerWin(dealerCards: PlayerCards): Boolean {
        val dealerPoint = dealerCards.getPoint()
        val gamerPoint = cards.getPoint()

        if (gamerPoint > BLACKJACK_MAX_NUMBER) {
            return false
        }
        return gamerPoint > dealerPoint
    }

    private fun continueToTurn(): Boolean {
        return cards.getPoint() < BLACKJACK_MAX_NUMBER
    }

    companion object {
        const val DOUBLE = 2.0
        const val DOUBLE_HALF = 1.5
        const val RESTORE = 1.0
        const val ZERO = 0.0
    }
}
