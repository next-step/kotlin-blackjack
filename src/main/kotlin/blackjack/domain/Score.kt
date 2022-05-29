package blackjack.domain

@JvmInline
value class Score(val cards: List<Card>) {

    val isBlackjack: Boolean
        get() = sum() == BLACKJACK

    val isBust: Boolean
        get() = sum() > BLACKJACK

    private fun sum(): Int {
        val (aces, remaining) = cards.partition { it.isAce }

        return remaining.sumOf {
            SCORE_MAP.getOrDefault(it.denomination, 0)
        }
    }

    companion object {
        private const val BLACKJACK = 21
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
