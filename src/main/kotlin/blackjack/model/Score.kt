package blackjack.model

@JvmInline
value class Score private constructor(val value: Int) {
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

        private fun needToAddScoreOfAce(cards: PlayingCards, currentScore: Int): Boolean {
            return cards.hasCardOf(CardNumber.ACE) && currentScore + ADDITIONAL_SCORE_OF_ACE <= BLACKJACK_SCORE
        }
    }
}
