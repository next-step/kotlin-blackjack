package blackjack.domain

@JvmInline
value class Rank(private val value: String) {
    init {
        require(value in RANK_VALUES) { "랭크는 $RANK_VALUES 에 포함되어야 합니다." }
    }

    val score: Int
        get() =
            when (value) {
                in NUMBER_VALUES -> value.toInt()
                in FACE_VALUES -> 10
                else -> throw IllegalArgumentException("에이스는 경우에 따라 점수를 합산해야 합니다")
            }

    companion object {
        private const val ACE_VALUE = "A"

        private val NUMBER_VALUES = listOf("2", "3", "4", "5", "6", "7", "8", "9")
        private val FACE_VALUES = listOf("J", "Q", "K")
        private val RANK_VALUES = NUMBER_VALUES + FACE_VALUES + ACE_VALUE

        val ACE = Rank(ACE_VALUE)
    }
}
