package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.card.DrewCards
import blackjack.domain.rule.DefaultScoringRule
import blackjack.domain.rule.ScoringRule
import blackjack.domain.rule.State

abstract class Player(open val name: String, scoringRule: ScoringRule) {
    protected val _cards: DrewCards = DrewCards(scoringRule)
    val cards: List<Card> get() = _cards.cards
    var state: State = State.BEGIN
        protected set
    var isFinished: Boolean = false
        get() = state.isFinished
        private set

    var totalScore: Int = 0
        get() = _cards.totalScore
        private set

    var profit: Int = 0
        private set

    fun beginGame(deck: Deck) {
        draw(deck)
        draw(deck)

        if (totalScore == DefaultScoringRule.THRESHOLD_SCORE) {
            state = State.BLACKJACK
        }
    }

    abstract fun draw(deck: Deck)

    abstract fun canDraw(): Boolean
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        return name == other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
