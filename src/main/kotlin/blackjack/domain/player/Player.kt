package blackjack.domain.player

import blackjack.domain.Cards
import blackjack.domain.Score

class Player(name: String, cards: Cards) : Gamer(name, cards) {

    override fun isTakeable(): Boolean {
        return cards.score < Score.BLACKJACK
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
