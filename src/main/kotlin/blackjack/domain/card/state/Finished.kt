package blackjack.domain.card.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class Finished(cards: Cards) : AbstractState(cards) {

    override fun draw(card: Card): State {
        throw IllegalStateException()
    }

    override fun stay(): State {
        throw IllegalStateException("stay가 될 수 없는 상태")
    }

    override fun isFinished() = true

    override fun isHit() = false
}
