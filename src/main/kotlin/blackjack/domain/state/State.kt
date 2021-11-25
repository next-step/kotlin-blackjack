package blackjack.domain.state

import blackjack.domain.deck.Card
import blackjack.domain.deck.Cards

interface State {
    val cards: Cards

    fun draw(card: Card): State
    fun isFinished(): Boolean

    fun isStand(playable: Boolean): Boolean {
        return !playable
    }

    companion object {
        const val FINISHED_SIGN = "n"
        const val CAN_PLAY = "y"
    }
}
