package blackjack.domain

data class Score private constructor(val value: Int) {
    override fun toString(): String {
        return "$value"
    }

    companion object {
        private val numbers = mutableMapOf<Int, Score>()

        fun of(value: Int): Score {
            return numbers[value] ?: Score(value).also { numbers[value] = it }
        }
    }
}
