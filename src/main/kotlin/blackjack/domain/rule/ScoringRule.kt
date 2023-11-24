package blackjack.domain.rule

import blackjack.domain.Card

interface ScoringRule {

    fun sumAll(cards: List<Card>): Int

    fun isOverThreshold(score: Int): Boolean
}
