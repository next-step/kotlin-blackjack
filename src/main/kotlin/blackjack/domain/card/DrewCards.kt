package blackjack.domain.card

import blackjack.domain.rule.ScoringRule

class DrewCards(private val scoringRule: ScoringRule) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card> get() = _cards.toList()

    val size: Int get() = _cards.size
    val totalScore: Int get() = scoringRule.sumAll(_cards)

    fun add(card: Card) {
        _cards.add(card)
    }
}
