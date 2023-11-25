package blackjack.domain.card

@JvmInline
value class HandScore(
    val value: Int,
) {
    val isBust: Boolean
        get() = value > BUST_THRESHOLD

    fun isGreaterThan(score: Int) = value > score

    companion object {
        private const val BUST_THRESHOLD = 21
        private const val ACE_BONUS_SCORE = 10

        fun from(hand: Hand): HandScore {
            val ranks = hand.ranks
            val score = ranks.sumOf { it.score() }
            return when {
                ranks.hasAce().not() -> score
                score + ACE_BONUS_SCORE > BUST_THRESHOLD -> score
                else -> score + ACE_BONUS_SCORE
            }.let(::HandScore)
        }

        private fun List<Rank>.hasAce(): Boolean = Rank.ACE in this

        private fun Rank.score(): Int = when (this) {
            Rank.ACE -> 1
            Rank.TWO -> 2
            Rank.THREE -> 3
            Rank.FOUR -> 4
            Rank.FIVE -> 5
            Rank.SIX -> 6
            Rank.SEVEN -> 7
            Rank.EIGHT -> 8
            Rank.NINE -> 9
            Rank.TEN,
            Rank.JACK,
            Rank.QUEEN,
            Rank.KING -> 10
        }
    }
}
