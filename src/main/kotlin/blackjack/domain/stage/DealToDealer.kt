package blackjack.domain.stage

import blackjack.domain.BlackJackGame
import blackjack.domain.result.DealToDealerResult

class DealToDealer : CardDistributor {
    override fun invoke(game: BlackJackGame): DealToDealerResult {
        var isHit = false
        if (game.dealer.isScoreGreaterThan(HIT_THRESHOLD).not()) {
            game.dealer.dealToSelf(COUNT_TO_DEAL)
            isHit = true
        }
        game.setDistributor(DistributionEnd())
        return DealToDealerResult(isHit)
    }

    companion object {
        private const val HIT_THRESHOLD = 16
        private const val COUNT_TO_DEAL = 1
    }
}
