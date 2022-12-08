package blackjack.domain.card.state

import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards

abstract class Finished(open val cards: PlayingCards) : Started() {
    override fun draw(playingCard: PlayingCard): State {
        throw IllegalStateException("이미 끝난 상태입니다.")
    }

    override fun stay(): State {
        throw IllegalStateException("이미 끝난 상태입니다.")
    }

    override fun isFinished(): Boolean {
        return true
    }
}
