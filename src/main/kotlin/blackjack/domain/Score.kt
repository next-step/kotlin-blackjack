package blackjack.domain

@JvmInline
value class Score(private val cards: List<Card>) {

    val isBlackjack: Boolean
        get() = sum == BLACKJACK_SUM

    val isBust: Boolean
        get() = sum > BLACKJACK_SUM

    val sum: Int
        get() {
            val (aces, remaining) = cards.partition { it.isAce }
            val remainingSum = remaining.sumOf {
                SCORE_MAP.getOrDefault(it.denomination, 0)
            }
            val outcomes = possibleOutcome(aces.size).map { it + remainingSum }

            if (outcomes.contains(BLACKJACK_SUM)) {
                return BLACKJACK_SUM
            }

            return outcomes.minOrNull() ?: 0
        }

    private fun possibleOutcome(aceCount: Int): List<Int> =
        when (aceCount) {
            1 -> listOf(1, 11)
            2 -> listOf(2, 12)
            else -> listOf(aceCount)
        }

    companion object {
        private const val BLACKJACK_SUM = 21
        private val SCORE_MAP = mapOf(
            Denomination.TWO to 2,
            Denomination.THREE to 3,
            Denomination.FOUR to 4,
            Denomination.FIVE to 5,
            Denomination.SIX to 6,
            Denomination.SEVEN to 7,
            Denomination.EIGHT to 8,
            Denomination.NINE to 9,
            Denomination.TEN to 10,
            Denomination.JACK to 10,
            Denomination.QUEEN to 10,
            Denomination.KING to 10,
        )
    }
}
