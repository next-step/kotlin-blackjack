package blackjack.domain.rule

import blackjack.domain.Card

class DefaultScoringRule : ScoringRule {

    override fun sumAll(cards: List<Card>): Int {
        val firstSumUsingAceTo11 = cards.sumOf { it.character.scores.first }
        if (firstSumUsingAceTo11 > THRESHOLD_SCORE) return cards.sumOf { it.character.scores.second }

        return firstSumUsingAceTo11
    }

    override fun isOverThreshold(score: Int): Boolean {
        return score > THRESHOLD_SCORE
    }

    companion object {
        const val THRESHOLD_SCORE = 21
    }
}
