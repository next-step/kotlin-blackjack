package blackjack.domain.rule

import blackjack.domain.card.Card

class TestScoringRule(private val score: Int) : ScoringRule {
    override fun sumAll(cards: List<Card>): Int {
        return score;
    }
}
