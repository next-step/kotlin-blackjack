package blackjack.domain

import blackjack.domain.state.Initial
import blackjack.domain.state.State

abstract class Participant(private val name: PlayerName, private val hand: Hand) {
    protected var state: State = Initial.initialState(hand)

    fun drawCard(deck: Deck) {
        state.draw(deck.drawCard())
    }

    fun calculateTotal(): Int {
        return hand.calculateBestTotal()
    }

    fun getName(): String {
        return name.value
    }

    fun getAllCards(): List<Card> {
        return hand.getAllCards()
    }

    abstract fun isDrawable(): Boolean

    abstract fun getInitialCard(): List<Card>
}
