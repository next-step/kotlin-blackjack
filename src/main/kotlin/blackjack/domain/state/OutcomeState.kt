package blackjack.domain.state

import blackjack.domain.Dealer
import blackjack.domain.Gamer

sealed class OutcomeState {

    abstract fun supported(playerType: StateType, dealerType: StateType): Boolean

    open fun update(gamer: Gamer, dealer: Dealer) {
        gamer.revenue += (gamer.bet * getGamerRate())
        dealer.revenue += (gamer.bet * getDealerRate())
    }

    abstract fun getGamerRate(): Double
    abstract fun getDealerRate(): Double
}
