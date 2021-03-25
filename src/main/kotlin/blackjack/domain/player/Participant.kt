package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Score

open class Participant(val name: Name, val cards: Cards) {
    val score: Score
        get() = cards.score

    fun take(card: Card) {
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
