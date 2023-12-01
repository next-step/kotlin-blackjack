package blackjack.domain

@JvmInline
value class Hand(val cards: MutableList<Card> = mutableListOf()) {

    fun add(card: Card) = cards.add(card)

    fun getCardCount() = cards.size

    fun getBestScore(): Score {
        val scoreSums = mutableSetOf<Score>()
        calculateScoreSums(cards.toList(), 0, Score.from(0), scoreSums)
        return when (scoreSums.minBy { it.value }.value <= Rule.BLACKJACK_SCORE) {
            true -> {
                scoreSums
                    .filter { it.value <= Rule.BLACKJACK_SCORE }
                    .maxBy { it.value }
            }
            false -> {
                scoreSums.minBy { it.value }
            }
        }
    }

    private fun calculateScoreSums(cards: List<Card>, index: Int, sum: Score, sums: MutableSet<Score>) {
        if (index == cards.size) {
            sums.add(sum)
            return
        }
        cards[index].getPossibleScoreSums(sum).forEach {
            calculateScoreSums(cards, index + 1, it, sums)
        }
    }
}
