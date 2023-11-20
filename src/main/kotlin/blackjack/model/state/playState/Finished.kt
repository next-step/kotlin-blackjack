package blackjack.model.state.playState

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.state.State

abstract class Finished(override val cardDeck: CardDeck) : State {
    override fun draw(card: Card): State {
        throw IllegalStateException(ALREADY_FINISH_MESSAGE)
    }

    override fun isFinished(): Boolean {
        return true
    }

    companion object {
        private const val ALREADY_FINISH_MESSAGE = "이미 끝난 상태입니다."
    }
}
