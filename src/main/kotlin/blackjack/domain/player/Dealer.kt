package blackjack.domain.player

import blackjack.domain.card.Deck
import blackjack.domain.rule.ScoringRule

class Dealer(scoringRule: ScoringRule) : Player(DEALER_NAME, scoringRule) {
    override fun draw(deck: Deck) {
        this.cards.add(deck.draw())
    }

    fun canDraw(): Boolean {
        return totalScore <= DEALER_THRESHOLD_SCORE
    }

    companion object {
        private const val DEALER_THRESHOLD_SCORE = 16
        private const val DEALER_NAME = "딜러"
    }
}
