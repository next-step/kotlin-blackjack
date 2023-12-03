package blackjack.domain.state

import blackjack.domain.GameResult
import blackjack.domain.Score
import blackjack.domain.card.Cards

class Stay(
    cards: Cards
) : Finished(cards) {
    override fun calculateResult(otherScore: Score): GameResult.Result {
        val score = cards().calculateScore()
        if (score == otherScore) {
            return GameResult.Result.DRAW
        }
        if (score < otherScore) {
            return GameResult.Result.LOSE
        }
        return GameResult.Result.WIN
    }
}
