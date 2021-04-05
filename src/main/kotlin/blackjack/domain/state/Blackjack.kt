package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards

data class Blackjack(override val cards: Cards) : State {

    override fun isTakeable() = false

    override fun draw(card: Card) = throw UnsupportedOperationException("Blackjack 은 카드를 뽑을 수 없다.")

    override fun stay() = throw UnsupportedOperationException("Blackjack 상태에서 Stay 로 바꿀 수 없다.")

    override fun isBust() = false
}
