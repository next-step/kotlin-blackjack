package blackjack.domain.state

import blackjack.domain.GameResult
import blackjack.domain.Score
import blackjack.domain.card.Cards

class Bust(
    cards: Cards
) : Finished(cards) {

    override fun calculateResult(otherScore: Score): GameResult.Result {
        return GameResult.Result.LOSE
    }
}
