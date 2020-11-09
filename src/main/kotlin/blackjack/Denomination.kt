package blackjack

enum class Denomination(private var score: Int, private val symbol: String) {
    ACE(1, "ACE"),
    TWO(2, "TWO"),
    THREE(3, "THREE"),
    FOUR(4, "FOUR"),
    FIVE(5, "FIVE"),
    SIX(6, "SIX"),
    SEVEN(7, "SEVEN"),
    EIGHT(8, "EIGHT"),
    NINE(9, "NINE"),
    JACK(10, "JACK"),
    QUEEN(10, "QUEEN"),
    KING(10, "KING");

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
