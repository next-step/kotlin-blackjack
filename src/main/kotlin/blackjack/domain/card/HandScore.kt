package blackjack.domain.card

@JvmInline
value class HandScore(
    val value: Int,
) {

    val isOverMaxScore: Boolean
        get() = value > MAX_SCORE

    companion object {
        private const val MAX_SCORE = 21
        private const val ACE_BONUS_SCORE = 10

        fun of(hand: Hand): HandScore {
            val ranks = hand.ranks
            val score = ranks.sumOf { it.score() }
            return when {
                !ranks.hasAce() -> score
                score + ACE_BONUS_SCORE > MAX_SCORE -> score
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
