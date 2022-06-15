package blackjack.domain

data class Score(private val cards: List<Card>) {

    val isBlackjack: Boolean
        get() = sum == BLACKJACK_SUM

    val isBust: Boolean
        get() = sum > BLACKJACK_SUM

    val sum: Int
        get() {
            val (aces, remaining) = cards.partition { it.isAce }
            val remainingSum = remaining.sum()
            val outcomes = possibleOutcome(aces.size).map { it + remainingSum }

            if (outcomes.contains(BLACKJACK_SUM)) {
                return BLACKJACK_SUM
            }

            val bestOutcome = outcomes.filter { it <= BLACKJACK_SUM }.maxOrNull()
            return bestOutcome ?: outcomes.firstOrNull() ?: 0
        }

    operator fun compareTo(other: Score): Int = this.sum.compareTo(other.sum)

    private fun List<Card>.sum(): Int = sumOf { it.denomination.score }

    private fun possibleOutcome(aceCount: Int): List<Int> =
        when (aceCount) {
            1, 2, 3, 4 -> listOf(aceCount, aceCount + Denomination.ACE.score)
            else -> listOf(aceCount)
        }

    companion object {
        private const val BLACKJACK_SUM = 21
    }
}
