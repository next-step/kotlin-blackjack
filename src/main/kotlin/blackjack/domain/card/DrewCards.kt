package blackjack.domain.card

import blackjack.domain.rule.DefaultScoringRule
import blackjack.domain.rule.ScoringRule

class DrewCards(private val scoringRule: ScoringRule) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card> get() = _cards.toList()
    val size: Int get() = _cards.size

    val totalScore: Int get() = scoringRule.sumAll(_cards)
    var state: State = State.BEGIN
        private set

    val isFinished: Boolean get() = state.isFinished

    fun add(card: Card) {
        _cards.add(card)
        applyState()
    }

    fun stay() {
        state = State.STAY
    }

    private fun applyState() {
        if (isBlackjack()) state = State.BLACKJACK
        if (isHit()) state = State.HIT
        if (isBust()) state = State.BUST
    }

    private fun isBlackjack() = (size == 2
            && state == State.BEGIN
            && totalScore == DefaultScoringRule.THRESHOLD_SCORE)

    private fun isHit() = (size > 2
            && (state == State.BEGIN || state == State.HIT)
            && totalScore < DefaultScoringRule.THRESHOLD_SCORE)

    private fun isBust() = (size > 2
            && (state == State.BEGIN || state == State.HIT)
            && totalScore > DefaultScoringRule.THRESHOLD_SCORE)
}
