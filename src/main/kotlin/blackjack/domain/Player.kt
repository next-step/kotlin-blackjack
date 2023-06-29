package blackjack.domain

import blackjack.domain.Cards.Companion.BLACK_JACK_SCORE
import blackjack.error.BlackjackErrorMessage.SUM_OVER_21

open class Player(
    val name: String,
    private val gameCardsSet: GameCardsSet,
) {
    private var myCards: Cards = Cards.empty()
    private var state: PlayerState = PlayerState.DEFAULT

    fun getMyCards(): Cards = myCards.copy()

    fun numberOfMyCards(): Int = myCards.cards.size

    fun sumOfMyCards(): Int = myCards.calculateOptimalSum()

    fun canDraw(): Boolean {
        val optimalSum = myCards.calculateOptimalSum()

        if (optimalSum == BLACK_JACK_SCORE) {
            findStateBySum()
            return false
        }

        if (optimalSum > BLACK_JACK_SCORE) {
            findStateBySum()
            return false
        }
        return true
    }

    fun stand() {
        state = PlayerState.STAND
    }

    fun findStateBySum(): PlayerState {
        val sumOfMyCards = sumOfMyCards()

        if (sumOfMyCards == BLACK_JACK_SCORE) {
            state = PlayerState.BLACK_JACK
        }

        if (sumOfMyCards < BLACK_JACK_SCORE) {
            state = PlayerState.STAND
        }

        if (sumOfMyCards > BLACK_JACK_SCORE) {
            state = PlayerState.BUST
        }

        return state
    }

    fun hit() {
        check(canDraw()) { SUM_OVER_21 }

        val drawnCard = gameCardsSet.drawRandomCard()
        myCards = myCards.add(drawnCard)
        state = PlayerState.HIT
    }
}
