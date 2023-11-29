package blackjack.participant

import blackjack.ScoreCalculator
import blackjack.card.BlackJackCard

class Dealer(
    scoreCalculator: ScoreCalculator
) : AbstractPlayer(scoreCalculator, "딜러") {
    override fun isDealer(): Boolean {
        return true
    }

    override fun shouldDraw(): Boolean {
        return resultScore() <= DEALER_SHOULD_DRAW_SCORE
    }

    companion object {
        private const val DEALER_SHOULD_DRAW_SCORE: Int = 16
    }
}
