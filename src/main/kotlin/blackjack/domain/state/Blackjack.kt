package blackjack.domain.state

import blackjack.domain.GameResult
import blackjack.domain.Score
import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class Blackjack(
    cards: Cards
) : Finished(cards) {
    override fun receiveCard(card: Card): State {
        throw IllegalStateException("카드를 받을 수 있는 상태가 아닙니다.")
    }

    override fun calculateResult(otherScore: Score): GameResult.Result {
        val score = cards().calculateScore()
        if (score == otherScore) {
            return GameResult.Result.DRAW
        }
        return GameResult.Result.WIN
    }
}
