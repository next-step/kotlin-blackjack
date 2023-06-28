package blackjack.domain.score

data class Score(
    val value: Int
) {

    val isAlive = value <= WIN_NUMBER
    val isBust = isAlive.not()

    operator fun compareTo(other: Int): Int {
        return value.compareTo(other)
    }

    operator fun compareTo(other: Score): Int {
        return value.compareTo(other.value)
    }

    companion object {

        private const val WIN_NUMBER = 21
    }
}
