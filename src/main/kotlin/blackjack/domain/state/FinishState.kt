package blackjack.domain.state

import blackjack.domain.card.PlayingCards

abstract class FinishState(playingCards: PlayingCards) : State(playingCards = playingCards) {

    abstract fun resultScore(): Int
}
