package blackjack

sealed class CardRank private constructor() {
    abstract fun calculateScore(currentScore: Int): Int

    data object Ace : CardRank() {
        override fun calculateScore(currentScore: Int): Int {
            return if (currentScore + 11 > 21) currentScore + 1 else currentScore + 11
        }
    }

    data object Face : CardRank() {
        override fun calculateScore(currentScore: Int): Int = currentScore + 10
    }

    data class Number(val value: Int) : CardRank() {
        init {
            require(value in 2..9) { "Number 는 2~9 사이의 값 이어야 합니다.: $value" }
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
