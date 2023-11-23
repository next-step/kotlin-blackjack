package blackjack.domain.stage

import blackjack.domain.BlackJackGame
import blackjack.domain.result.DealInitialCardResult

class DealInitialCards : CardDistributor {

    override fun invoke(game: BlackJackGame): DealInitialCardResult {
        game.dealCardsToAllPlayers(INITIAL_DISTRIBUTION_COUNT)
        game.dealCardToDealer(INITIAL_DISTRIBUTION_COUNT)
        game.setDistributor(DealToPlayer())
        return DealInitialCardResult(game.dealer, game.players)
    }

    companion object {
        private const val INITIAL_DISTRIBUTION_COUNT = 2
    }
}
