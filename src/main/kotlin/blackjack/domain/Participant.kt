package blackjack.domain

import blackjack.error.BlackjackErrorMessage.CAN_NOT_DRAW

abstract class Participant {
    private var cards: Cards = Cards.empty()
    var state: PlayerState = PlayerState.HIT
        protected set

    fun getCards(): Cards = cards.copy()

    fun sizeOfCards(): Int = cards.cards.size

    fun sumOfCards(): Int = cards.calculateOptimalSum()

    fun stand() {
        state = PlayerState.STAND
    }

    fun hit(card: Card) {
        check(state.canDraw) { CAN_NOT_DRAW }
        cards = cards.add(card)
        state = PlayerState.of(this)
    }
}
