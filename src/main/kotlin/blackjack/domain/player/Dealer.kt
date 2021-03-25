package blackjack.domain.player

import blackjack.domain.Cards
import blackjack.domain.Score

class Dealer(name: String, cards: Cards) : Gamer(name, cards) {

    override fun isNotTakeable(): Boolean {
        return cards.score > Score.DEALER_TAKEABLE_LIMIT
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
