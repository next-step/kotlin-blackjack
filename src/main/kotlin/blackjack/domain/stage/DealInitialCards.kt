package blackjack.domain.stage

import blackjack.domain.BlackJackGame
import blackjack.domain.GameTable
import blackjack.domain.result.DealInitialCardResult

class DealInitialCards : CardDistributor {

    override fun invoke(game: BlackJackGame, table: GameTable): DealInitialCardResult {
        table.dealToAll(INITIAL_DISTRIBUTION_COUNT)
        game.setDistributor(DealToPlayer())
        return DealInitialCardResult(game.table.dealer, game.table.players)
    }

    companion object {
        private const val INITIAL_DISTRIBUTION_COUNT = 2
    }
}
