package blackjack

object BlackjackCardPointCalculator {
    fun calculate(cards: Set<Card>) : Int{
        val pointCandidates: MutableList<Int> = mutableListOf()
        fillAllPointCase(pointCandidates, cards.toList(), 0, 0)
        return pointCandidates.filter { it <= 21 }.maxOf { it }
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
