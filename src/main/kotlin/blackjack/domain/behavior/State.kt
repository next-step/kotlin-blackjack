package blackjack.domain.behavior

import blackjack.domain.card.Card
import blackjack.domain.card.PlayingCards
import blackjack.domain.model.BlackJackErrorCode

sealed class State(val playingCards: PlayingCards) {

    open fun hit(card: Card): State = throwDoneInState()

    open fun stay(): FinishState = throwDoneInState()

    open fun resultScore(): Int = throwDoneInState()

    open fun availableTurn(): Boolean = false

    protected fun throwDoneInState(): Nothing = throw IllegalStateException(
        BlackJackErrorCode.CAN_NOT_DONE_IN_THE_STATE.message(
            arrayOf(this)
        )
    )
}
