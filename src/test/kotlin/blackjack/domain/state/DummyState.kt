package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards

class DummyState(override val cards: Cards) : State {
    override fun isTakeable(): Boolean {
        throw UnsupportedOperationException("this is dummy.")
    }

    override fun draw(card: Card): State {
        throw UnsupportedOperationException("this is dummy.")
    }

    override fun stay(): Stay {
        throw UnsupportedOperationException("this is dummy.")
    }
}
