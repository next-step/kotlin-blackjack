package blackjack.domain

import blackjack.domain.state.HittableState

internal abstract class Player(val name: String, val betting: Int = 5000) {
    var state: State = HittableState(PlayerCards())

    abstract val maxHittableScore: Int
    abstract val visibleCards: List<Card>

    val cards: List<Card>
        get() {
            return this.state.cards
        }

    fun acceptCard(card: Card) {
        if (!canHit()) {
            throw IllegalStateException("only hittable")
        }

        this.state = state.addCard(card)
    }

    fun canHit(): Boolean {
        return maxHittableScore > this.score() && this.state.hittable
    }

    fun score(): Int {
        return state.score()
    }
}
