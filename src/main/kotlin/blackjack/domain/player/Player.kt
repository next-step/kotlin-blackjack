package blackjack.domain.player

import blackjack.domain.Cards
import blackjack.domain.Score

class Player(name: String, cards: Cards) : Gamer(name, cards) {

    override fun isNotTakeable(): Boolean {
        return cards.score >= Score.BLACKJACK
    }

    fun isTakeableDealer(): Boolean {
        return cards.score <= Score.DEALER_TAKEABLE_LIMIT
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
