package blackjack.domain.player

import blackjack.domain.deck.Cards
import blackjack.domain.deck.Deck
import blackjack.domain.player.Bust.Companion.BUST_START_SCORE

class Hit(cards: Cards) : Status(cards) {
    override fun draw(deck: Deck): Status {
        cards.add(deck.draw())
        val calculateScore = calculate()
        if (calculateScore >= BUST_START_SCORE) {
            return Bust(cards)
        }
        return this
    }

    override fun stay(): Status {
        return Stay(cards)
    }

    override fun isFinished(): Boolean {
        return false
    }
}
