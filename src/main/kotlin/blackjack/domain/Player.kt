package blackjack.domain

import blackjack.domain.state.HittableState

internal abstract class Player(val name: String, val betting: Int = 5000) {
    var state: State = HittableState(PlayerCards())

    abstract val maxHitScore: Int
    abstract val visibleCards: List<Card>

    val cards: List<Card>
        get() {
            return this.state.cards
        }

    fun acceptCard(card: Card) {
        this.state = state.addCard(card)
    }

    fun canHit(): Boolean {
        return this.state.canHit(maxHitScore)
    }

    fun score(): Int {
        return state.score()
    }
}
