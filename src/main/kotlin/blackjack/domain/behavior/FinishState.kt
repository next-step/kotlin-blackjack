package blackjack.domain.behavior

import blackjack.domain.card.Card
import blackjack.domain.card.PlayingCards

class FinishState(playingCards: PlayingCards) : State(playingCards = playingCards) {

    override fun hit(card: Card): State = throwDoneInState()

    override fun stay(): FinishState = throwDoneInState()

    override fun resultScore(): Int = playingCards.calculateOptimalScore()
}
