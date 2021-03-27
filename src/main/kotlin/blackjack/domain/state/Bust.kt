package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards

data class Bust(override val cards: Cards) : State {

    override fun draw(card: Card): State {
        throw UnsupportedOperationException("Bust 는 카드를 뽑을 수 없다.")
    }

    override fun stay(): Stay {
        throw UnsupportedOperationException("Bust 상태에서 Stay 로 바꿀 수 없다.")
    }
}
