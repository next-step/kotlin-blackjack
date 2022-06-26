package blackjack.domain.card

private const val MAX_SCORE = 21

class Hand {
    private var _value: MutableList<Card> = mutableListOf()

    val value
        get() = _value.toList()

    fun add(card: Card) {
        _value.add(card)
    }

    fun isBust(): Boolean {
        return getScore() > MAX_SCORE
    }

    fun getScore(): Int {
        var sumOf = _value.sumOf { it.rank.score }
        _value.forEach { sumOf -= getScoreDifferenceIfBust(sumOf, it.rank) }
        return sumOf
    }

    private fun getScoreDifferenceIfBust(sumOf: Int, rank: Rank) = if (sumOf > MAX_SCORE) rank.getScoreDifference() else 0
}
