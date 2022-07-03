package blackjack.domain.participant.state

import blackjack.domain.participant.GameResult

class Stay(cards: Cards) : Finished(cards) {
    override fun judgementPlayerResult(otherScore: Score): GameResult {
        return when {
            otherScore.isBlackjack -> GameResult.LOSE
            otherScore.isBust -> GameResult.WIN
            else -> this.score().compareGameResult(other = otherScore)
        }
    }
}
