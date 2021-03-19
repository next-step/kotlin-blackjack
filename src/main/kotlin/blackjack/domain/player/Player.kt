package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Score

class Player(val name: String, val cards: Cards) {
    init {
        require(name.isNotBlank())
    }

    fun isNotTakeable(): Boolean {
        return cards.score >= Score.BLACKJACK
    }

    fun takeCard(card: Card) {
        cards.add(card)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Player) return false

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
