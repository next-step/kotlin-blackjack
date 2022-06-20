package blackjack.domain.game

import blackjack.domain.game.strategy.TakeMoreDealerViewStrategy
import blackjack.domain.game.strategy.TakeMorePlayerStrategy
import blackjack.domain.player.Player

class TakeMoreDealer(private val takeMoreDealerViewStrategy: TakeMoreDealerViewStrategy) : TakeMorePlayerStrategy {

    override fun wantToTake(player: Player): Boolean {
        return player.score <= DEALER_TAKE_ONE_CARD_POLICY
    }

    fun printTakeMoreDealer() {
        takeMoreDealerViewStrategy.printDoneTakeMore()
    }

    companion object {
        const val DEALER_TAKE_ONE_CARD_POLICY = 16
    }
}
