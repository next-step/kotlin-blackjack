package blackjack.domain.card

import kotlin.math.abs

@JvmInline
value class Cards(
    val values: Set<Card> = linkedSetOf(),
) {
    init {
        require(values.size == values.toSet().size) { "중복된 카드가 저장될 수 없다." }
    }

    fun addCard(card: Card): Cards {
        require(values.contains(card).not()) { "이미 존재하는 카드를 추가할 수 없다." }
        return Cards(values + card)
    }

    fun isInitialHand(): Boolean = values.size < INITIAL_HAND_CARD_LIMIT_SIZE

    fun isBust(): Boolean = values.sumOf { it.denomination.score.min() } > BLACKJACK_SCORE

    fun isBlackjack(): Boolean = score() == BLACKJACK_SCORE

    fun score(): Int {
        check(values.isNotEmpty()) { "카드가 없어 점수를 계산할 수 없다." }
        return calculateOptimalScore(calculateAllAvailableScore())
    }

    private fun calculateOptimalScore(scores: List<Int>) = scores.filter { it <= BLACKJACK_SCORE }
        .maxOrNull() ?: scores.minBy { abs(BLACKJACK_SCORE - it) }

    private fun calculateAllAvailableScore() = values.map { it.denomination.score }
        .reduce(::aggregateScore)

    private fun aggregateScore(
        currentScores: List<Int>,
        nextScores: List<Int>
    ) = currentScores.flatMap { currentScore -> nextScores.map { nextScore -> nextScore + currentScore } }

    companion object {
        private const val BLACKJACK_SCORE = 21
        private const val INITIAL_HAND_CARD_LIMIT_SIZE = 2

        fun of(vararg card: Card) = Cards(card.toCollection(LinkedHashSet()))
    }
}
