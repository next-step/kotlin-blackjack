package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.state.Hit
import blackjack.domain.state.State

class Player(override val name: String, override var state: State) : Gamer {

    init {
        require(name.isNotBlank())
    }

    override fun isTakeable(): Boolean {
        return state is Hit
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
