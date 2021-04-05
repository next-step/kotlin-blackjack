package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards

class DummyState(override val cards: Cards) : State {

    override fun isTakeable() = throw UnsupportedOperationException("this is dummy.")

    override fun draw(card: Card) = throw UnsupportedOperationException("this is dummy.")

    override fun stay() = throw UnsupportedOperationException("this is dummy.")

    override fun isBust() = throw UnsupportedOperationException("this is dummy.")
}
