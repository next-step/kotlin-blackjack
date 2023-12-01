package blackjack.domain.rule

import blackjack.domain.card.Card

interface ScoringRule {

    fun sumAll(cards: List<Card>): Int
}
