package blackjack.domain.player

import blackjack.domain.GameResult
import blackjack.domain.card.Deck
import blackjack.domain.rule.DefaultScoringRule
import blackjack.domain.rule.ScoringRule
import blackjack.domain.rule.State

class Dealer(private val scoringRule: ScoringRule) : Player(DEALER_NAME, scoringRule) {
    override fun draw(deck: Deck) {
        this._cards.add(deck.draw())

        if (scoringRule.isOverThreshold(totalScore, DefaultScoringRule.THRESHOLD_SCORE)) {
            state = State.BUST
        }
    }

    override fun canDraw(): Boolean {
        return scoringRule.isOverThreshold(totalScore, DEALER_THRESHOLD_SCORE).not()
    }

    fun compareWith(participant: Participant): GameResult {
        val dealerTotalScore = this.totalScore

        return when {
            (dealerTotalScore > DefaultScoringRule.THRESHOLD_SCORE) -> GameResult.LOSE
            (participant.totalScore > DefaultScoringRule.THRESHOLD_SCORE) -> GameResult.WIN
            (dealerTotalScore == participant.totalScore) -> GameResult.TIE
            (dealerTotalScore > participant.totalScore) -> GameResult.WIN
            else -> GameResult.LOSE
        }
    }

    companion object {
        private const val DEALER_THRESHOLD_SCORE = 16
        private const val DEALER_NAME = "딜러"
    }
}