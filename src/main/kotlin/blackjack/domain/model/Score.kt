package blackjack.domain.model

@JvmInline
value class Score private constructor(val value: Int) {

    init {
        require(value in MIN_VALUE..MAX_VALUE) {
            "스코어는 $MIN_VALUE~$MAX_VALUE 사이의 값이여야 합니다."
        }
    }

    companion object {
        private const val MIN_VALUE = 1
        private const val MAX_VALUE = 31
        fun from(score: Int) = Score(score)
    }
}
