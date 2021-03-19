package blackjack.domain

internal enum class CardNumber(val score: Int, val displayName: String = score.toString()) {

    ACE(1, "A"),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    companion object {
        fun score(numbers: List<CardNumber>): Int {
            val aceNumberCount = numbers.count { it == ACE }
            var sum = numbers.sumBy { it.score }

            repeat(aceNumberCount) {
                if (sum + ACE_SECOND_SCORE_GAP <= BlackJackGame.MAX_SCORE) {
                    sum += ACE_SECOND_SCORE_GAP
                }
            }
            return sum
        }

        private const val ACE_SECOND_SCORE_GAP = 10
    }
}
