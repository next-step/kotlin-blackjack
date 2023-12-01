package blackjack.domain.player

import blackjack.domain.card.Deck
import blackjack.domain.card.DrewCards
import blackjack.domain.rule.ScoringRule

abstract class Player(open val name: String, scoringRule: ScoringRule) {
    val cards: DrewCards = DrewCards(scoringRule)
    var isFinished: Boolean = false
        get() = cards.isFinished
        private set

    var totalScore: Int = 0
        get() = cards.totalScore
        private set

    var profit: Int = 0

    abstract fun draw(deck: Deck)

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
