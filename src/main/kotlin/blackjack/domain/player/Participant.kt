package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Score
import blackjack.domain.card.state.State

open class Participant(val name: Name, _state: State) {
    var state: State = _state
        protected set

    val score: Score
        get() = state.score

    fun take(card: Card) {
        state = state.draw(card)
    }

    fun isBust() = state.isBust()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Player) return false

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
