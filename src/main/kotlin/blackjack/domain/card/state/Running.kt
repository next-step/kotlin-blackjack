package blackjack.domain.card.state

import blackjack.domain.card.PlayingCards

abstract class Running(override var cards: PlayingCards) : State {
    override fun isFinished(): Boolean {
        return false
    }
}
