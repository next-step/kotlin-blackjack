package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand

class Stay(
    hand: Hand,
) : Started(hand) {
    override fun draw(card: Card): State2 {
        throw IllegalArgumentException("Stay 상태라면 카드를 더이상 뽑을 수 없습니다.")
    }

    override fun stay(): State2 {
        throw IllegalArgumentException("Stay 상태라면 카드를 더이상 뽑을 수 없습니다.")
    }

    override fun isFinished(): Boolean {
        return true
    }

    override fun isBlackjack(): Boolean {
        return false
    }
}
