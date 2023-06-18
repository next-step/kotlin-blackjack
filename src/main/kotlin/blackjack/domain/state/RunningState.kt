package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.PlayingCards
import blackjack.domain.state.finish.FinishState

abstract class RunningState(playingCards: PlayingCards) : State(playingCards = playingCards) {

    abstract fun draw(card: Card): State

    abstract fun stay(): FinishState
}
