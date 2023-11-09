package blackjack.domain

import blackjack.domain.Score.Companion.ACE_BONUS
import blackjack.domain.Score.Companion.BLACK_JACK_SCORE

@JvmInline
value class Cards(val cards: MutableSet<Card> = mutableSetOf()) {

    fun add(card: Card) {
        cards.add(card)
    }

    fun remove(card: Card) {
        cards.remove(card)
    }

    fun score(): Score {
        val score = cards.sumOf { it.rank.score }
        if (hasAce() && score + ACE_BONUS <= BLACK_JACK_SCORE) {
            return Score(score + ACE_BONUS)
        }
        return Score(score)
    }

    private fun hasAce(): Boolean {
        return cards.any { it.rank == Rank.ACE }
    }
}
