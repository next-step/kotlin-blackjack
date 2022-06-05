package blackjack.domain

@JvmInline
value class Score private constructor(val value: Int) {
    fun isBlackjack(): Boolean = value == BLACKJACK_SCORE

    fun isBust(): Boolean = value > BLACKJACK_SCORE

    fun canAddMore(): Boolean = value < BLACKJACK_SCORE

    companion object {
        private const val BLACKJACK_SCORE = 21
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

        fun zero(): Score = Score(0)

        private fun needToAddScoreOfAce(cards: PlayingCards, currentScore: Int): Boolean {
            return cards.hasCardOf(CardNumber.ACE) && currentScore + ADDITIONAL_SCORE_OF_ACE <= BLACKJACK_SCORE
        }
    }
}
