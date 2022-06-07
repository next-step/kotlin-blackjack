package blackjack.domain.player

import blackjack.domain.game.TakeMoreDealerStrategy

class Dealer(private val takeMoreDealerStrategy: TakeMoreDealerStrategy) : Player(_name = DEALER_NAME) {

    fun canBeTakeOneCard(): Boolean {
        return takeMoreDealerStrategy.canBeTakeOneCard(this.score)
    }

    companion object {
        private const val DEALER_NAME = "딜러"
    }
}
