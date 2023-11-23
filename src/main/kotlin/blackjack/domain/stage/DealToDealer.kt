package blackjack.domain.stage

import blackjack.domain.Action
import blackjack.domain.BlackJackGame
import blackjack.domain.GameTable
import blackjack.domain.result.DealToDealerResult

class DealToDealer : CardDistributor {
    override fun invoke(game: BlackJackGame, table: GameTable): DealToDealerResult {
        val action = table.dealer.hitOrStand()
        if (action == Action.HIT) table.dealer.dealToSelf(COUNT_TO_DEAL)
        game.setDistributor(DistributionEnd())
        return DealToDealerResult(action == Action.HIT)
    }

    companion object {
        private const val COUNT_TO_DEAL = 1
    }
}
