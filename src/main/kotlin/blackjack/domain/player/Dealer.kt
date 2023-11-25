package blackjack.domain.player

import blackjack.domain.GameResult
import blackjack.domain.card.Deck
import blackjack.domain.rule.DefaultScoringRule
import blackjack.domain.rule.ScoringRule

class Dealer(private val scoringRule: ScoringRule) : Player(DEALER_NAME, scoringRule) {
    override fun draw(deck: Deck) {
        this._cards.add(deck.draw())
    }

    override fun canDraw(): Boolean {
        return scoringRule.isOverThreshold(totalScore, DEALER_THRESHOLD_SCORE).not()
    }

    fun compareWith(participant: Participant): GameResult {
        if (this.totalScore > DefaultScoringRule.THRESHOLD_SCORE) return GameResult.LOSE
        if (participant.totalScore > DefaultScoringRule.THRESHOLD_SCORE) return GameResult.WIN
        if (this.totalScore == participant.totalScore) return GameResult.TIE
        return if (this.totalScore > participant.totalScore) GameResult.WIN else GameResult.LOSE
    }

    companion object {
        private const val DEALER_THRESHOLD_SCORE = 16
        private const val DEALER_NAME = "딜러"
    }
}