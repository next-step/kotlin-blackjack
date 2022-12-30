package blackjack.domain.card.state

import blackjack.domain.card.PlayingCards

abstract class Running(override val cards: PlayingCards) : State() {
    override fun isFinished(): Boolean {
        return false
    }
}
