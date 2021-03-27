package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards

data class Blackjack(override val cards: Cards) : State {

    override fun draw(card: Card): State {
        throw UnsupportedOperationException("Blackjack 은 카드를 뽑을 수 없다.")
    }

    override fun stay(): Stay {
        throw UnsupportedOperationException("Blackjack 상태에서 Stay 로 바꿀 수 없다.")
    }
}
