package blackjack.domain

class Score(
    val value: Int
) : Comparable<Score> {

    override fun compareTo(other: Score): Int {
        return value.compareTo(other.value)
    }

    companion object {
        const val BLACK_JACK_SCORE = 21
        const val STANDARD_CARD_SCORE = 16
    }
}
