package blackjack.model.card

class CardScore(
    val score1: Int,
    val score2: Int,
) {

    init {
        validateMinScore(score1)
        validateMinScore(score2)
    }

    fun findNearestScoreEqualOrLessThan(boundaryScore: Int): Int {
        val maxScore = maxOf(score1, score2)
        if (maxScore <= boundaryScore) {
            return maxScore
        }

        val minScore = minOf(score1, score2)
        if (minScore <= boundaryScore) {
            return minScore
        }

        throw IllegalArgumentException("${boundaryScore}점 보다 작거나 같으면서 가장 가까운 점수를 찾을 수 없습니다.")
    }

    fun isAllScoreGreaterThan(other: Int) = score1 > other && score2 > other

    fun isOneOfScoreLessThan(other: Int) = score1 < other || score2 < other

    fun isOneOfScoreEqualOrLessThan(other: Int) = score1 <= other || score2 <= other

    fun isOneOfScoreBlackJack() = score1 == BLACK_JACK_SCORE || score2 == BLACK_JACK_SCORE

    private fun validateMinScore(score: Int) = require(score >= MIN_SCORE) { "카드 점수는 최소 ${MIN_SCORE}점 이상이어야 합니다." }

    operator fun plus(other: CardScore): CardScore {
        val sumOfScore = score1 + other.score1
        val sumOfOtherScore = score2 + other.score2
        return CardScore(sumOfScore, sumOfOtherScore)
    }

    companion object {
        private const val MIN_SCORE = 0
        private const val BLACK_JACK_SCORE = 21

        val ZERO = CardScore(0, 0)
    }
}
