package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.card.Card

class Stay(override val hand: Hand) : State(hand) {
    override fun draw(card: Card): State {
        throw IllegalStateException("카드를 더 받을 수 없습니다.")
    }
}
