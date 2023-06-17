package blackjack.domain.behavior

import blackjack.domain.card.Card
import blackjack.domain.card.PlayingCards

class BustState(playingCards: PlayingCards) : State(playingCards = playingCards) {

    override fun hit(card: Card): State = throwDoneInState()

    override fun stay(): FinishState = throwDoneInState()

    override fun resultScore(): Int = ZERO

    companion object {
        private const val ZERO: Int = 0
    }
}
