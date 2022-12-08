package blackjack.domain.card.state

import blackjack.domain.card.PlayingCards

abstract class Running(open val cards: PlayingCards) : Started() {
    override fun isFinished(): Boolean {
        return false
    }
}
