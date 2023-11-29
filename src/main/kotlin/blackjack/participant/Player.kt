package blackjack.participant

import blackjack.ScoreCalculator
import blackjack.card.BlackJackCard

class Player(
    name: String,
    scoreCalculator: ScoreCalculator
) : AbstractPlayer(scoreCalculator, name) {
    override fun isDealer(): Boolean {
        return false
    }

    override fun shouldDraw(): Boolean {
        return resultScore() <= BUST
    }

    companion object {
        private const val BUST: Int = 21
    }
}
