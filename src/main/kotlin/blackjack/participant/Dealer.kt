package blackjack.participant

import blackjack.ScoreCalculator

class Dealer(
    scoreCalculator: ScoreCalculator
) : AbstractPlayer(scoreCalculator, DEALER_NAME) {
    override fun isDealer(): Boolean {
        return true
    }

    override fun shouldDraw(): Boolean {
        return resultScore() <= DEALER_SHOULD_DRAW_SCORE
    }

    companion object {
        private const val DEALER_SHOULD_DRAW_SCORE: Int = 16
        private const val DEALER_NAME: String = "딜러"
    }
}
