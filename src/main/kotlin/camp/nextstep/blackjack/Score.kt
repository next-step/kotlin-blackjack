package camp.nextstep.blackjack

data class Score(val value: Int) : Comparable<Score> {

    val isBlackJack: Boolean = value == BLACK_JACK_SCORE

    val isBust: Boolean = value > BLACK_JACK_SCORE

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

        private val SCORES = (0..30).associateWith { Score(it) }

        private val BLACK_JACK = of(BLACK_JACK_SCORE)

        private val BIG_ACE = of(11)

        private val SMALL_ACE = of(1)

        private val ACE_BONUS = BIG_ACE - SMALL_ACE

        private fun canAddAceBonus(score: Score) = BLACK_JACK >= score + ACE_BONUS

        fun of(cards: Collection<Card>): Score {
            var score = of(cards.sumOf { it.number.value })

            val aceCount = cards.filter { it.number == CardNumber.ACE }.size
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
