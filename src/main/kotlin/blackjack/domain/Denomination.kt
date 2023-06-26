package blackjack.domain

enum class Denomination(
    private val score: Int,
) {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10),
    ;

    fun score(): Score = Score(score)

    fun isAce(): Boolean {
        return this == ACE
    }

    fun maxScore(): Score {
        if (this == ACE) {
            return Score.MAX_ACE
        }

        return score()
    }
}
