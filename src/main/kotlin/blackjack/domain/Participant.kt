package blackjack.domain

import blackjack.domain.state.Initial
import blackjack.domain.state.State

abstract class Participant(private val name: PlayerName) {
    var state: State = Initial.initialState(Hand.createInitial(emptyList()))
        private set

    fun addCard(card: Card) {
        state = state.draw(card)
    }

    fun calculateTotal(): Int {
        return state.calculateTotal()
    }

    fun isBlackjack(): Boolean {
        return state.isBlackjack()
    }

    fun isBust(): Boolean {
        return state.isBust()
    }

    fun getName(): String {
        return name.value
    }

    fun getAllCards(): List<Card> {
        return state.getAllCards()
    }

    abstract fun isDrawable(): Boolean

    abstract fun getInitialCard(): List<Card>
}
