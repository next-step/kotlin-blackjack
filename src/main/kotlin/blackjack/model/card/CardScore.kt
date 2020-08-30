package blackjack.model.card

enum class CardScore(val initial: String, val score: Int) {
    ACE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    companion object {
        private const val ACE_ELEVEN_AVAILABLE_SCORE = 11
        private const val SCORE_ADDED_WHEN_USING_ACE_ELEVEN = 10

        fun sumWithAce(sum: Int, hasAce: Boolean): Int {
            var sumWithAce = sum
            if (sumWithAce <= ACE_ELEVEN_AVAILABLE_SCORE && hasAce) {
                sumWithAce += SCORE_ADDED_WHEN_USING_ACE_ELEVEN
            }
            return sumWithAce
        }
    }
}
