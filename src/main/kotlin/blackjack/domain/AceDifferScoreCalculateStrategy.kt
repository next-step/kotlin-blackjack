package blackjack.domain

private const val SPECIAL_ACE_SCORE = 11
private const val NORMAL_ACE_SCORE = 1
private const val BLACK_JACK_SCORE = 21

object AceDifferScoreCalculateStrategy : ScoreCalculateStrategy {
    override fun calculate(cards: List<Card>): Score {
        val notAceCards = cards.filterNot { it.isAce() }
        val scoresWithoutAce = notAceCards.sumOf { it.number.score }
        val aceCount = cards.size - notAceCards.size

        if (aceCount == 0) {
            return Score(scoresWithoutAce)
        }

        val specialAceCount = 1
        val restAceCount = aceCount - specialAceCount

        if (scoresWithoutAce + (SPECIAL_ACE_SCORE * specialAceCount) + (NORMAL_ACE_SCORE * restAceCount) > BLACK_JACK_SCORE) {
            return Score(scoresWithoutAce + NORMAL_ACE_SCORE * aceCount)
        }

        return Score(scoresWithoutAce + SPECIAL_ACE_SCORE + restAceCount)
    }
}
