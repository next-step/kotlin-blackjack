package blackjack

object BlackjackCardPointCalculator {

    private const val BLACKJACK_POINT_THRESHOLD = 21

    fun calculate(cards: List<Card>) : Int{
        val pointCandidates: MutableList<Int> = mutableListOf()
        fillAllPointCase(pointCandidates, cards.toList(), 0, 0)
        return pointCandidates.filter { it <= BLACKJACK_POINT_THRESHOLD }.maxOf { it }
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
