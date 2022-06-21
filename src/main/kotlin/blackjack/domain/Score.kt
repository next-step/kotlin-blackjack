package blackjack.domain

@JvmInline
value class Score private constructor(val value: Int) : Comparable<Score> {
    fun isBlackjack(): Boolean = value == BLACKJACK_SCORE

    fun isBust(): Boolean = value > BLACKJACK_SCORE

    fun canAddMore(): Boolean = value < BLACKJACK_SCORE

    override fun compareTo(other: Score): Int = value.compareTo(other.value)

    companion object {
        const val BLACKJACK_SCORE = 21
        private const val ADDITIONAL_SCORE_OF_ACE = 10

        fun from(cards: PlayingCards): Score {
            val baseScore = cards.sumOf { playingCard ->
                playingCard.number.score
            }

            val result = if (needToAddScoreOfAce(cards, baseScore)) {
                baseScore + ADDITIONAL_SCORE_OF_ACE
            } else {
                baseScore
            }

            return Score(result)
        }

        fun from(value: Int): Score {
            return Score(value)
        }

        private fun needToAddScoreOfAce(cards: PlayingCards, currentScore: Int): Boolean {
            return CardNumber.ACE in cards && currentScore + ADDITIONAL_SCORE_OF_ACE <= BLACKJACK_SCORE
        }
    }
}
