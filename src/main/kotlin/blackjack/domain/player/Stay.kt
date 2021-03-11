package blackjack.domain.player

import blackjack.domain.deck.Cards
import blackjack.domain.deck.Deck

class Stay(cards: Cards) : Status(cards) {
    override fun draw(deck: Deck): Status {
        throw IllegalArgumentException("이미 끝났습니다")
    }

    override fun stay(): Status {
        throw IllegalArgumentException("이미 끝났습니다")
    }

    override fun isFinished(): Boolean {
        return true
    }
}
