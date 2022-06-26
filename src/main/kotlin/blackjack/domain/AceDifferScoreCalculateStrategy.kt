package blackjack.domain

import blackjack.vo.Score

private const val SPECIAL_ACE_SCORE = 11
const val BLACK_JACK_SCORE = 21

object AceDifferScoreCalculateStrategy : ScoreCalculateStrategy {
    override fun calculate(hand: Hand): Score {
        val notAceCards = hand.filterNot { it.isAce() }
        val scoresWithoutAce = notAceCards.sumOf { it.cardNumber.score }
        val countAce = hand.size - notAceCards.size

        if (countAce == 0) {
            return Score(scoresWithoutAce)
        }

        if (scoresWithoutAce + SPECIAL_ACE_SCORE + (countAce - 1) > BLACK_JACK_SCORE) {
            return Score(scoresWithoutAce + countAce)
        }

        return Score(scoresWithoutAce + SPECIAL_ACE_SCORE + countAce - 1)
    }
}
