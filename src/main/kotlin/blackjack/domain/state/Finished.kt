package blackjack.domain.state

import blackjack.domain.card.Card

abstract class Finished : State {
    override val isFinished: Boolean = true
    override fun draw(card: Card): State {
        throw IllegalStateException("더 이상 카드를 받을 수 없습니다.")
    }
}
