package blackjack.domain

object BlackjackCardPointCalculator {

    const val BLACKJACK_POINT_THRESHOLD = 21

    /**
     * ### 카드의 점수 합계를 계산하는 메서드 입니다.
     */
    fun calculate(cards: List<Card>): Int {
        val pointSumCandidates: List<Int> = getPointSumCandidates(cards.toList(), 0, 0)
        return electPointSumFrom(pointSumCandidates)
    }

    /**
     * ### 카드의 모든 점수 합계 후보를 추출하는 메서드입니다.
     *
     * ex) 주어진 카드가 ACE(point = 1, 11), Jack(point = 10)인 경우 11(1 + 10), 21(11 + 10)이 점수 합계 후보로 추출됩니다.
     */
    private fun getPointSumCandidates(
        cards: List<Card>,
        pointSum: Int,
        depth: Int
    ) : List<Int> {
        if (depth == cards.size) {
            return listOf(pointSum)
        }
        var list: List<Int> = listOf()
        cards[depth].points.forEach { point ->
            list = list + getPointSumCandidates(cards, pointSum + point, depth + 1)
        }
        return list
    }

    /**
     * ### 점수 합계 후보중 BLACKJACK_POINT_THRESHOLD 값에 가장 가까운 점수 합계를 추출합니다.
     *
     * 21 이하의 점수가 존재하면 21에 가까운 최고 점수를, 아니라면 21에 가까운 최저 점수를 리턴합니다.
     * @see BLACKJACK_POINT_THRESHOLD
     */
    private fun electPointSumFrom(pointSumCandidates: List<Int>): Int {
        val under21Candidates = pointSumCandidates.filter { it <= BLACKJACK_POINT_THRESHOLD }
        val over21Candidates = pointSumCandidates.filter { it > BLACKJACK_POINT_THRESHOLD }
        return when (under21Candidates.isNotEmpty()) {
            true -> under21Candidates.maxOf { it }
            false -> over21Candidates.minOf { it }
        }
    }
}
