package blackjack.domain

import blackjack.controller.GameController.BLACK_JACK_SCORE

enum class NumberType(
    val score: Score,
) {
    TWO(Score.from(2)),
    THREE(Score.from(3)),
    FOUR(Score.from(4)),
    FIVE(Score.from(5)),
    SIX(Score.from(6)),
    SEVEN(Score.from(7)),
    EIGHT(Score.from(8)),
    NINE(Score.from(9)),
    TEN(Score.from(10)),
    ACE(Score.from(1)),
    KING(Score.from(10)),
    QUEEN(Score.from(10)),
    JACK(Score.from(10));

    companion object {
        private const val MIN_ACE_SCORE = 1
        private const val MAX_ACE_SCORE = 11

        fun getScore(cards: Cards): Score {
            var sum = cards.cards
                .filter { it.numberType != ACE }
                .sumOf { it.numberType.score.score }
                .let { Score.from(it) }

            repeat(cards.countAce()) {
                sum += getAceScore(sum)
            }
            return sum
        }

        private fun getAceScore(totalScore: Score): Score =
            if (Score.from(MAX_ACE_SCORE) + totalScore > Score.from(BLACK_JACK_SCORE)) {
                Score.from(MIN_ACE_SCORE)
            } else {
                Score.from(MAX_ACE_SCORE)
            }
    }
}
