package blackjack.domain.participant.state

import blackjack.domain.participant.GameResult

class Blackjack(cards: Cards) : Finished(cards) {
    override fun judgementPlayerResult(otherScore: Score): GameResult {
        return if (otherScore.isBlackjack) {
            GameResult.DRAW
        } else {
            GameResult.WIN
        }
    }
}
