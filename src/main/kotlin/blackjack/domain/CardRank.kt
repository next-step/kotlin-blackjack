package blackjack.domain

sealed class CardRank private constructor() {
    abstract fun calculateScore(currentScore: Int): Int

    data object Ace : CardRank() {
        private const val SOFT_SCORE = 11
        private const val HARD_SCORE = 1
        private const val BUST_SCORE = 21

        override fun calculateScore(currentScore: Int): Int {
            return if (currentScore + SOFT_SCORE > BUST_SCORE) currentScore + HARD_SCORE else currentScore + SOFT_SCORE
        }
    }

    data object Face : CardRank() {
        override fun calculateScore(currentScore: Int): Int = currentScore + 10
    }

    data class Number(val value: Int) : CardRank() {
        init {
            val numberMin = 2
            val numberMax = 9
            require(value in numberMin..numberMax) { "Number 는 $numberMin~$numberMax 사이의 값 이어야 합니다.: $value" }
        }

        override fun calculateScore(currentScore: Int): Int = currentScore + value
    }

    companion object {
        private val allowedValues = listOf("A", "J", "Q", "K") + (2..9).map { it.toString() }

        fun from(value: String): CardRank {
            return when (value) {
                "A" -> Ace
                "J", "Q", "K" -> Face
                in allowedValues -> Number(value.toInt())
                else -> throw IllegalArgumentException("RankType 은 A, J, Q, K, 2~9 만 가능합니다.: $value")
            }
        }
    }
}
