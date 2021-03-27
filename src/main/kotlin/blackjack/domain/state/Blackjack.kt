package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards

data class Blackjack(override val cards: Cards) : State {

    override fun draw(card: Card): State {
        throw UnsupportedOperationException("Blackjack 은 카드를 뽑을 수 없다.")
    }
}
