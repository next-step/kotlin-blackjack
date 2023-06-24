package blackjack.domain.state

import blackjack.domain.Dealer
import blackjack.domain.Gamer

object Stay : OutcomeState() {
    private const val WIN_RATE = 1.0
    private const val LOSE_RATE = -1.0

    private var gamerRate = 0.0
    private var dealerRate = 0.0

    override fun supported(playerType: StateType, dealerType: StateType): Boolean =
        playerType == StateType.STAY && dealerType == StateType.STAY

    override fun update(gamer: Gamer, dealer: Dealer) {
        val (newGamerRate, newDealerRate) = if (gamer.calculateScore() > dealer.calculateScore()) {
            Pair(WIN_RATE, LOSE_RATE)
        } else {
            Pair(LOSE_RATE, WIN_RATE)
        }

        gamerRate = newGamerRate
        dealerRate = newDealerRate

        super.update(gamer, dealer)
    }

    override fun getGamerRate(): Double = gamerRate

    override fun getDealerRate(): Double = dealerRate
}
