package blackjack.domain.card

class HandScore(
    val cardScore: Int,
) {
    val isBust: Boolean
        get() = cardScore > BUST_THRESHOLD

    val isGreaterOrEqualToMaxScore: Boolean = cardScore >= BUST_THRESHOLD

    val isBlackJackScore: Boolean = cardScore == BLACK_JACK_SCORE

    val gameScore: Int
        get() {
            if (isBust) return BUST_SCORE
            return cardScore
        }

    infix fun isGreaterCardScoreThan(score: Int) = cardScore > score
    infix fun isGreaterGameScoreThan(other: HandScore) = gameScore > other.gameScore
    infix fun isSameGameScoreTo(other: HandScore) = gameScore == other.gameScore

    companion object {
        private const val BUST_THRESHOLD = 21
        private const val BLACK_JACK_SCORE = 21
        private const val BUST_SCORE = 0
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
