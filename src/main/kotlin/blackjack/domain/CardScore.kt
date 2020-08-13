package blackjack.domain

enum class CardScore(val score: Int, val initial: String = "") {
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
        const val ACE_ELEVEN_AVAILABLE_SCORE = 11
        const val ADDED_SCORE_WHEN_USING_ELEVEN_ACE = 10

        fun initialOfCard(cardScore: CardScore): String {
            return cardScore.initial
        }

        fun sumWithAce(sum: Int, hasAce: Boolean): Int {
            var sumWithAce = sum
            if (sumWithAce <= ACE_ELEVEN_AVAILABLE_SCORE && hasAce) {
                sumWithAce += ADDED_SCORE_WHEN_USING_ELEVEN_ACE
            }
            return sumWithAce
        }
    }
}
