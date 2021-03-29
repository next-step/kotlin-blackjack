package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.Score
import blackjack.domain.state.State

class Dealer(override val name: String, override var state: State) : Gamer {

    val score: Score
        get() = state.cards.score

    override fun isTakeable(): Boolean {
        return state.isTakeable() && score <= Score.DEALER_TAKEABLE_LIMIT
    }

    override fun takeCard(card: Card) {
        state = state.draw(card)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
