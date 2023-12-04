package blackjack.domain.state

import blackjack.domain.GameResult
import blackjack.domain.Score
import blackjack.domain.card.Cards

class Blackjack(
    cards: Cards
) : Finished(cards) {
    override fun calculateResult(otherScore: Score): GameResult.Result {
        val score = cards().calculateScore()
        if (score == otherScore) {
            return GameResult.Result.DRAW
        }
        return GameResult.Result.WIN
    }
}
