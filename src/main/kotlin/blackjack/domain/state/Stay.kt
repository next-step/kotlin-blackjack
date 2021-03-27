package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards

data class Stay(override val cards: Cards) : State {

    override fun draw(card: Card): State {
        throw UnsupportedOperationException("Stay 는 카드를 뽑을 수 없다.")
    }
}
