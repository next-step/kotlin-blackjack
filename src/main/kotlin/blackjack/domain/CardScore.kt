package blackjack.domain

enum class CardScore(private val score: Int, private val initial: String) {
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
        private const val ACE_ELEVEN_AVAILABLE_SCORE = 11
        private const val SCORE_ADDED_WHEN_USING_ACE_ELEVEN = 10

        fun scoreOfCard(cardScore: CardScore): Int = cardScore.score

        fun initialOfCard(cardScore: CardScore): String = cardScore.initial

        fun sumWithAce(sum: Int, hasAce: Boolean): Int {
            var sumWithAce = sum
            if (sumWithAce <= ACE_ELEVEN_AVAILABLE_SCORE && hasAce) {
                sumWithAce += SCORE_ADDED_WHEN_USING_ACE_ELEVEN
            }
            return sumWithAce
        }
    }
}
