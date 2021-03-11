package blackjack.domain.player

import blackjack.domain.deck.Cards
import blackjack.domain.deck.Deck

class Blackjack(cards: Cards) : Status(cards) {
    override fun draw(deck: Deck): Status {
        throw IllegalArgumentException("블랙잭인데 왜 더 뽑아요")
    }

    override fun stay(): Status {
        throw IllegalArgumentException("이미 끝났습니다")
    }

    override fun isFinished(): Boolean {
        return true
    }

    companion object {
        const val BLACKJACK_SCORE = 21
    }
}
