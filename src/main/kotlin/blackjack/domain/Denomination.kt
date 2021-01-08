package blackjack.domain

enum class Denomination(private var score: Int, private val symbol: String) {
    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    companion object {
        const val MAX_SCORE = 11
        const val MIN_SCORE = 1

        fun getScore(Denomination: Denomination): Int {
            return Denomination.score
        }

        fun getSymbol(Denomination: Denomination): String {
            return Denomination.symbol
        }

        fun AceCalculator(totalScore: Int): Int {
            if (totalScore < 10) {
                return MAX_SCORE
            }
            return MIN_SCORE
        }
    }
}
