package camp.nextstep.blackjack.game

import camp.nextstep.blackjack.card.Card
import camp.nextstep.blackjack.card.CardNumber

@JvmInline
value class Score(val value: Int) : Comparable<Score> {

    fun isBlackJack(): Boolean = value == BLACK_JACK_SCORE

    fun isBust(): Boolean = value > BLACK_JACK_SCORE

    fun isNotBust(): Boolean = !isBust()

    override fun compareTo(other: Score): Int {
        return this.value.compareTo(other.value)
    }

    operator fun plus(other: Score): Score {
        return of(this.value + other.value)
    }

    operator fun minus(other: Score): Score {
        return of(this.value - other.value)
    }

    companion object {

        private const val BLACK_JACK_SCORE = 21

        private val CACHEABLE_RANGE = 0..30

        private val SCORES = CACHEABLE_RANGE.associateWith { Score(it) }

        private val BLACK_JACK = of(BLACK_JACK_SCORE)

        private val BIG_ACE = of(11)

        private val SMALL_ACE = of(1)

        private val ACE_BONUS = BIG_ACE - SMALL_ACE

        private fun canAddAceBonus(score: Score) = BLACK_JACK >= score + ACE_BONUS

        fun of(cards: Collection<Card>): Score {
            var score = of(cards.sumOf { it.number.value })

            val aceCount = cards.count { it.number == CardNumber.ACE }
            repeat(aceCount) {
                if (canAddAceBonus(score)) score += ACE_BONUS
            }

            return score
        }

        fun of(score: Int): Score {
            return SCORES[score] ?: Score(score)
        }
    }
}
