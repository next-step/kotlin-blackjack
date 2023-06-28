package blackjack.domain

class Score(
    val value: Int
) {
    init {
        require(value in START_SCORE..END_SCORE) {
            println(ERROR_SCORE_RANGE)
        }
    }

    companion object {
        const val START_SCORE = 1
        const val END_SCORE = 11
        const val ERROR_SCORE_RANGE = "점수는 1 ~ 11점 까 가능합니다"
    }
}
