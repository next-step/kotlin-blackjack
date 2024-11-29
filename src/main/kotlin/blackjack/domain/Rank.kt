package blackjack.domain

@JvmInline
value class Rank(private val value: String) {
    init {
        require(value in RANK_VALUES) { "랭크는 $RANK_VALUES 에 포함되어야 합니다" }
    }

    val score: Int
        get() =
            when (value) {
                in NUMBER_VALUES -> value.toInt()
                in FACE_VALUES -> FACE_SCORE
                ACE_VALUE -> DEFAULT_ACE_SCORE
                else -> throw IllegalArgumentException("유효하지 않은 랭크 값입니다")
            }

    companion object {
        private const val FACE_SCORE = 10
        private const val DEFAULT_ACE_SCORE = 11

        private const val ACE_VALUE = "A"
        private val NUMBER_VALUES = listOf("2", "3", "4", "5", "6", "7", "8", "9", "10")
        private val FACE_VALUES = listOf("J", "Q", "K")

        private val RANK_VALUES = NUMBER_VALUES + FACE_VALUES + ACE_VALUE

        val ACE = Rank(ACE_VALUE)
    }
}
