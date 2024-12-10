package blackjack.step2.domain

import blackjack.step2.domain.Participant.Companion.BLACKJACK_SCORE

enum class CardNumber(val score: Int, val denomination: String) {
    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    KING(10, "K"),
    QUEEN(10, "Q"),
    JACK(10, "J"),
    ;

    fun calculateScore(currentScore: Int): Int {
        return if (this == ACE && currentScore + 10 <= BLACKJACK_SCORE) {
            11 // ACE를 11로 사용할 수 있는 경우
        } else {
            score // 기본 점수
        }
    }

    companion object {
        fun random(): CardNumber {
            return entries.random()
        }
    }
}
