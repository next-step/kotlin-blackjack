package blackjack.domain.state

import blackjack.domain.Dealer
import blackjack.domain.Gamer
import blackjack.domain.Money

object Stay : OutcomeState() {
    private const val WIN_RATE = 1.0
    private const val LOSE_RATE = -1.0

    override fun supported(playerType: StateType, dealerType: StateType): Boolean =
        playerType == StateType.STAY && dealerType == StateType.STAY

    override fun getRevenue(gamer: Gamer, dealer: Dealer): Pair<Money, Money> {
        val (newGamerRate, newDealerRate) = if (gamer.calculateScore() > dealer.calculateScore()) {
            WIN_RATE to LOSE_RATE
        } else {
            LOSE_RATE to WIN_RATE
        }

        gamerRate = newGamerRate
        dealerRate = newDealerRate

        return super.getRevenue(gamer, dealer)
    }
}
