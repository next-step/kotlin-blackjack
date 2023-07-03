package blackjack.domain

import blackjack.error.BlackjackErrorMessage.CAN_NOT_DRAW

abstract class Participant {
    private var myCards: Cards = Cards.empty()
    var state: PlayerState = PlayerState.HIT
        protected set

    fun getMyCards(): Cards = myCards.copy()

    fun sizeOfMyCards(): Int = myCards.cards.size

    fun sumOfMyCards(): Int = myCards.calculateOptimalSum()

    fun stand() {
        state = PlayerState.STAND
    }

    fun hit(card: Card) {
        check(state.canDraw) { CAN_NOT_DRAW }
        myCards = myCards.add(card)
        state = PlayerState.of(this)
    }
}
