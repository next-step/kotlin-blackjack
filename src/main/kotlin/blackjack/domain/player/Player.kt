package blackjack.domain.player

import blackjack.domain.Cards
import blackjack.domain.Score

class Player(override val name: String, override val cards: Cards) : Gamer {

    init {
        require(name.isNotBlank())
    }

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
