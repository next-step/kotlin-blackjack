package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand

sealed class Finished(hand: Hand) : Running(hand) {
    override fun draw(card: Card): State {
        throw IllegalStateException("이미 종료된 상태입니다.")
    }

    override fun isFinished() = true
}

class Bust(hand: Hand) : Finished(hand)

class Blackjack(hand: Hand) : Finished(hand)
