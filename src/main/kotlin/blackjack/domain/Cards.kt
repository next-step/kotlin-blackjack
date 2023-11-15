package blackjack.domain

import blackjack.domain.Score.Companion.ACE_BONUS
import blackjack.domain.Score.Companion.BLACK_JACK_SCORE

class Cards(cards: Set<Card> = emptySet()) {

    private val _cards: MutableSet<Card> = cards.toMutableSet()
    val cards: Set<Card> get() = _cards.toSet()

    constructor(other: Cards) : this(other.cards.map { it.copy() }.toSet())

    fun add(card: Card) {
        _cards.add(card)
    }

    fun remove(card: Card) {
        _cards.remove(card)
    }

    fun score(): Score {
        val score = cards.sumOf { it.rank.score }
        if (hasAce() && score + ACE_BONUS <= BLACK_JACK_SCORE) {
            return Score(score + ACE_BONUS)
        }
        return Score(score)
    }

    fun take(value: Int): Cards {
        return Cards(_cards.take(value).toSet())
    }

    private fun hasAce(): Boolean {
        return cards.any { it.rank == Rank.ACE }
    }
}
