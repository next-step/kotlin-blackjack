package blackjack.model.card

class CardScore(
    val score: Int,
    val otherScore: Int,
) {

    init {
        validateMinScore(score)
        validateMinScore(otherScore)
    }

    fun plus(other: CardScore): CardScore {
        val sumOfScore = score.plus(other.score)
        val sumOfOtherScore = otherScore.plus(other.otherScore)
        return CardScore(sumOfScore, sumOfOtherScore)
    }

    private fun validateMinScore(score: Int) {
        require(score >= MIN_SCORE) { "카드 점수는 최소 ${MIN_SCORE}점 이상이어야 합니다." }
    }

    companion object {
        private const val MIN_SCORE = 1
    }
}
