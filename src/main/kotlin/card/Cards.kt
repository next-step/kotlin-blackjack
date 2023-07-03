package card

import kotlin.math.abs

@JvmInline
value class Cards(
    val values: Set<Card> = linkedSetOf(),
) {
    fun score(): Int {
        require(values.size >= NUMBER_OF_START_CARD)
        val availableScores = availableScore()
        return availableScores.filter { it <= BLACKJACK_MAX_SCORE }.maxOrNull()
            ?: availableScores.minBy { abs(BLACKJACK_MAX_SCORE - it) }
    }

    fun isBust(): Boolean = availableScore().all { it > BLACKJACK_MAX_SCORE }

    private fun availableScore(): List<Int> =
        values.map { it.denominations.score }
            .reduce(::aggregateScore)

    private fun aggregateScore(
        currentScores: List<Int>,
        nextScores: List<Int>,
    ): List<Int> = currentScores.flatMap { currentScore -> nextScores.map { nextScore -> nextScore + currentScore } }

    companion object {
        private const val BLACKJACK_MAX_SCORE = 21
        private const val NUMBER_OF_START_CARD = 2
    }
}
