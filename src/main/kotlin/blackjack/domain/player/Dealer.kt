package blackjack.domain.player

import blackjack.domain.game.TakeMorePlayerStrategy

class Dealer(takeMoreDealerStrategy: TakeMorePlayerStrategy) : Player(_name = DEALER_NAME, takeMoreDealerStrategy) {

    var win : Int = 0
    var lose : Int = 0

    fun canBeTakeOneCard(): Boolean {
        return takeMorePlayerStrategy.canBeTakeOneCard(this.score)
    }

    companion object {
        private const val DEALER_NAME = "딜러"
    }
}
