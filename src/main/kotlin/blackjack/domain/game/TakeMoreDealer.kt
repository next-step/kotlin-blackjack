package blackjack.domain.game

import blackjack.domain.game.strategy.TakeMoreDealerStrategy
import blackjack.domain.game.strategy.TakeMoreDealerViewStrategy

class TakeMoreDealer(private val takeMoreDealerViewStrategy: TakeMoreDealerViewStrategy) : TakeMoreDealerStrategy {

    override fun canBeTakeOneCard(score: Int): Boolean {
        return score <= DEALER_TAKE_ONE_CARD_POLICY
    }

    fun printTakeMoreDealer() {
        takeMoreDealerViewStrategy.printDoneTakeMore()
    }

    companion object {
        const val DEALER_TAKE_ONE_CARD_POLICY = 16
    }
}
