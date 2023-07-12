package blackjack.domain

class Score(
    val value: Int
) : Comparable<Score> {

    override fun compareTo(other: Score): Int {
        return value.compareTo(other.value)
    }

    fun plus(other: Int): Int {
        return this.value + other
    }

    fun isBlackjack(): Boolean {
        return value == BLACK_JACK_SCORE.value
    }

    companion object {
        val BLACK_JACK_SCORE: Score = Score(21)
        val STANDARD_CARD_SCORE: Score = Score(16)
    }
}
