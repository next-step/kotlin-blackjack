package blackjack.domain

data class Score(val score: Int) {
    init {
        require(score in (MIN_SCORE..MAX_SCORE)) { "점수의 범위는 ${MIN_SCORE}이상 ${MAX_SCORE}이하입니다.." }
    }

    fun isLowerThan(score: Int): Boolean {
        return this.score <= score
    }

    fun compareTo(other: Score): ResultType = ResultType.of(score.compareTo(other.score))

    companion object {
        private const val MIN_SCORE = 0
        private const val MAX_SCORE = 30
    }
}
