package blackjack.domain.state

import blackjack.domain.Dealer
import blackjack.domain.Gamer
import blackjack.domain.Money

sealed class OutcomeState(
    protected var gamerRate: Double = 0.0,
    protected var dealerRate: Double = 0.0
) {
    abstract fun supported(playerType: StateType, dealerType: StateType): Boolean

    open fun getRevenue(gamer: Gamer, dealer: Dealer): Pair<Money, Money> =
        (gamer.bet * gamerRate) to (gamer.bet * dealerRate)
}
