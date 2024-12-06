package blackjack.domain

import blackjack.domain.state.Initial
import blackjack.domain.state.State

abstract class Participant(private val name: PlayerName, private val hand: Hand) {
    protected var state: State = Initial.initialState(hand)

    fun addCard(card: Card) {
        hand.addCard(card)
    }

    fun getCards(): List<Card> {
        return hand.getCards()
    }

    fun calculateTotal(): Int {
        return hand.calculateBestTotal()
    }

    fun getName(): String {
        return name.value
    }

    abstract fun isDrawable(): Boolean

    abstract fun getInitialCard(): List<Card>
}
