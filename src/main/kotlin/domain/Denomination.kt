package domain

enum class Denomination(private val score: Int, private val symbol: String) {
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
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    companion object {
        const val MAX_ACE_SCORE = 11
        const val MIN_total_SCORE = 10

        fun getScore(cardScore: Denomination): Int {
            return cardScore.score
        }
    }
}
