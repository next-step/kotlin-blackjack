package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards

data class Stay(override val cards: Cards) : State {

    override fun isTakeable() = false

    override fun draw(card: Card) = throw UnsupportedOperationException("Stay 는 카드를 뽑을 수 없다.")

    override fun stay() = throw UnsupportedOperationException("Stay 상태에서 Stay 로 바꿀 수 없다.")

    override fun isBust() = false
}
