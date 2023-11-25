package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand

class Bust(
    hand: Hand,
) : Started(hand) {
    override fun draw(card: Card): State {
        throw IllegalArgumentException("버스트 되었다면 카드를 더이상 뽑을 수 없습니다.")
    }

    override fun stay(): State {
        throw IllegalArgumentException("버스트 되었다면 카드를 더이상 Stay 할 수 없습니다.")
    }

    override fun isFinished(): Boolean {
        return true
    }

    override fun isBlackjack(): Boolean {
        return false
    }
}
