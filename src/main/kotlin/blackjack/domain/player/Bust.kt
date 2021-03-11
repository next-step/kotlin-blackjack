package blackjack.domain.player

import blackjack.domain.deck.Cards
import blackjack.domain.deck.Deck

class Bust(cards: Cards) : Status(cards) {
    override fun draw(deck: Deck): Status {
        throw IllegalArgumentException("이미 끝났습니다")
    }

    override fun stay(): Status {
        throw IllegalArgumentException("이미 끝났습니다")
    }

    override fun isFinished(): Boolean {
        return true
    }

    companion object {
        const val BUST_START_SCORE = 22
    }
}
