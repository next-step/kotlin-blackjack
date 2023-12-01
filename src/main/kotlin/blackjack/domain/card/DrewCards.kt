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
        if (size == 2
            && state == State.BEGIN
            && totalScore == DefaultScoringRule.THRESHOLD_SCORE
        ) {
            state = State.BLACKJACK
        }

        if (size > 2
            && (state == State.BEGIN || state == State.HIT)
            && totalScore < DefaultScoringRule.THRESHOLD_SCORE
        ) {
            state = State.HIT
        }

        if (size > 2
            && (state == State.BEGIN || state == State.HIT)
            && totalScore > DefaultScoringRule.THRESHOLD_SCORE
        ) {
            state = State.BUST
        }
    }
}
