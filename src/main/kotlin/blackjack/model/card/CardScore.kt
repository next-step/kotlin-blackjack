package blackjack.model.card

class CardScore(
    val score1: Int,
    val score2: Int,
) {

    init {
        validateMinScore(score1)
        validateMinScore(score2)
    }

    operator fun plus(other: CardScore): CardScore {
        val sumOfScore = score1.plus(other.score1)
        val sumOfOtherScore = score2.plus(other.score2)
        return CardScore(sumOfScore, sumOfOtherScore)
    }

    fun isGreaterThan(other: Int) = score1 > other || score2 > other

    fun isLessThan(other: Int) = score1 < other || score2 < other

    fun isEqualOrLessThan(other: Int) = score1 <= other || score2 <= other

    private fun validateMinScore(score: Int) = require(score >= MIN_SCORE) { "카드 점수는 최소 ${MIN_SCORE}점 이상이어야 합니다." }

    companion object {
        private const val MIN_SCORE = 0

        val ZERO = CardScore(0, 0)
    }
}
