package blackjack.domain

object BlackjackCardPointCalculator {

    const val BLACKJACK_POINT_THRESHOLD = 21

    fun calculate(cards: List<Card>): Int {
        val pointCandidates: MutableList<Int> = mutableListOf()
        fillAllPointCase(pointCandidates, cards.toList(), 0, 0)

        val under21Candidates = pointCandidates.filter { it <= BLACKJACK_POINT_THRESHOLD }
        val over21Candidates = pointCandidates.filter { it > BLACKJACK_POINT_THRESHOLD }
        return when (under21Candidates.isNotEmpty()) {
            true -> under21Candidates.maxOf { it }
            false -> over21Candidates.minOf { it }
        }
    }

    private fun fillAllPointCase(pointCandidates: MutableList<Int>, cards: List<Card>, pointSum: Int, depth: Int) {
        if (depth == cards.size) {
            pointCandidates.add(pointSum)
            return
        }
        cards[depth].points.forEach { point ->
            fillAllPointCase(pointCandidates, cards, pointSum + point, depth + 1)
        }
    }
}
