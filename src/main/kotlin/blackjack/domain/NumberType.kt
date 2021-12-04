package blackjack.domain

import blackjack.controller.GameController.BLACK_JACK_SCORE

enum class NumberType(
    val score: Int,
) {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    ACE(1),
    KING(10),
    QUEEN(10),
    JACK(10);

    companion object {
        private const val MIN_ACE_SCORE = 1
        private const val MAX_ACE_SCORE = 11

        fun getScore(cards: Cards): Int {
            var sum = cards.cards
                .filter { it.numberType != ACE }
                .sumOf { it.numberType.score }

            repeat(cards.countAce()) {
                sum += getAceScore(sum)
            }
            return sum
        }

        private fun getAceScore(totalScore: Int): Int = if (MAX_ACE_SCORE + totalScore > BLACK_JACK_SCORE) {
            MIN_ACE_SCORE
        } else {
            MAX_ACE_SCORE
        }
    }
}
