package blackjack

enum class Numbers(val score: Int, val shape: String) {
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
    JACK(10, "J");

    companion object {
        private const val MAX_ACE_SCORE = 11
        private const val MIN_ACE_SCORE = 1
        private const val MIN_SCORE = 10

        fun ofScore(totalScore: Int, number: Numbers): Int {
            return when (number) {
                ACE -> getAceScore(totalScore)
                else -> number.score
            }
        }

        private fun getAceScore(total: Int): Int {
            if (total <= MIN_SCORE) return MAX_ACE_SCORE
            return MIN_ACE_SCORE
        }
    }
}
