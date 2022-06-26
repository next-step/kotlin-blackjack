package game.blackjack.domain

@JvmInline
value class Score(private val value: Int) {

    fun isBust(): Boolean = value > BLACKJACK_SCORE

    operator fun plus(score: Score) = Score(this.value + score.value)

    fun calculateAceScore(aceCount: Int): Score {
        var total = value
        repeat(aceCount) {
            total += if (total + ACE_EXTRA_SCORE <= BLACKJACK_SCORE) ACE_EXTRA_SCORE else 0
        }
        return Score(total)
    }

    fun toInt(): Int = value

    companion object {
        private const val ACE_EXTRA_SCORE = 10
        private const val BLACKJACK_SCORE = 21
    }
}

inline fun <T> Iterable<T>.sumOf(selector: (T) -> Score): Score {
    var sum: Score = Score(0)
    for (element in this) {
        sum += selector(element)
    }
    return sum
}
