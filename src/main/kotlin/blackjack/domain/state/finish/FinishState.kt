package blackjack.domain.state.finish

import blackjack.domain.card.PlayingCards
import blackjack.domain.state.State

abstract class FinishState(playingCards: PlayingCards) : State(playingCards = playingCards) {

    abstract fun resultScore(): Int
}
