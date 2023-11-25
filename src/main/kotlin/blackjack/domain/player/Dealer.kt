package blackjack.domain.player

import blackjack.domain.card.Deck
import blackjack.domain.rule.ScoringRule

class Dealer(private val scoringRule: ScoringRule) : Player(scoringRule) {
    override fun draw(deck: Deck) {
        this._cards.add(deck.draw())
    }

    override fun canDraw(): Boolean {
        return scoringRule.isOverThreshold(totalScore, THRESHOLD_SCORE).not()
    }

    companion object {
        const val THRESHOLD_SCORE = 16
    }
}