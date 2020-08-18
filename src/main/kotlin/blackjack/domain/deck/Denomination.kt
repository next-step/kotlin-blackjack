package blackjack.domain.deck

enum class Denomination(private val score: Int, val value: String) {
    ACE(11, "A"),
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
        private const val ACE_MIN = 1
        private const val ACE_MAX = 11
        private const val BLACKJACK_COUNT = 21

        fun scoreOf(denomination: Denomination, totalScore: Int = 0) = when (denomination) {
            ACE -> getAceScore(totalScore)
            else -> denomination.score
        }

        private fun getAceScore(totalScore: Int) = if (totalScore <= BLACKJACK_COUNT - ACE_MAX) ACE_MAX else ACE_MIN
    }
}
