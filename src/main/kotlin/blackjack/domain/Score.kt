package blackjack.domain

class Score(
    val value: Int
) : Comparable<Int> {

    override fun compareTo(other: Int): Int {
        return value.compareTo(other)
    }

    companion object {
        const val BLACK_JACK_SCORE = 21
        const val STANDARD_CARD_SCORE = 16
    }
}
