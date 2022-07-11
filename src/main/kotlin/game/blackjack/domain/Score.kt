package game.blackjack.domain

@JvmInline
value class Score(private val value: Int) {

    fun isBust(): Boolean = value > BLACKJACK_SCORE

    fun isBlackJack(): Boolean = value == BLACKJACK_SCORE

    fun sumIfSoftHand(hasAce: Boolean): Score {
        var total = value
        if (hasAce && total + ACE_EXTRA_SCORE <= BLACKJACK_SCORE) {
            total += ACE_EXTRA_SCORE
        }
        return Score(total)
    }

    fun toInt(): Int = value

    operator fun plus(score: Score) = Score(this.value + score.value)

    operator fun compareTo(other: Score): Int = value - other.value

    companion object {
        private const val ACE_EXTRA_SCORE = 10
        private const val BLACKJACK_SCORE = 21
    }
}

inline fun <T> Iterable<T>.sumOf(selector: (T) -> Score): Score {
    var sum = Score(0)
    for (element in this) {
        sum += selector(element)
    }
    return sum
}
