package blackjack.domain

import blackjack.domain.Cards.Companion.BLACK_JACK_SCORE
import blackjack.error.BlackjackErrorMessage.CAN_NOT_DRAW

open class Player(
    val name: String,
    private val gameCardsSet: GameCardsSet,
) {
    private var myCards: Cards = Cards.empty()
    private var state: PlayerState = PlayerState.HIT

    fun getMyCards(): Cards = myCards.copy()

    fun numberOfMyCards(): Int = myCards.cards.size

    fun sumOfMyCards(): Int = myCards.calculateOptimalSum()

    fun getState(): PlayerState = state

    fun canDraw(): Boolean = state.canDraw

    fun hit() {
        check(canDraw()) { CAN_NOT_DRAW }

        val drawnCard = gameCardsSet.drawRandomCard()
        myCards = myCards.add(drawnCard)
        updateState()
    }

    fun stand() {
        state = PlayerState.STAND
    }

    private fun updateState() {
        if (state == PlayerState.STAND) {
            return
        }

        val sumOfMyCards = sumOfMyCards()

        if (sumOfMyCards == BLACK_JACK_SCORE) {
            state = PlayerState.BLACK_JACK
        }

        if (sumOfMyCards < BLACK_JACK_SCORE) {
            state = PlayerState.HIT
        }

        if (sumOfMyCards > BLACK_JACK_SCORE) {
            state = PlayerState.BUST
        }
    }
}
