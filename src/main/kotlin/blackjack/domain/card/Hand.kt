package blackjack.domain.card

private const val MAX_SCORE = 21

class Hand {
    private var _value: MutableList<Card> = mutableListOf()

    val value2
        get() = _value.toList()

    fun add(card: Card) {
        _value.add(card)
    }

    fun getScore(): Int {
        var sumOf = _value.sumOf { it.rank.score }
        _value.forEach { sumOf -= getScoreDifferenceIfBust(sumOf, it.rank) }
        return sumOf
    }

    private fun getScoreDifferenceIfBust(sumOf: Int, rank: Rank) = if (sumOf > MAX_SCORE) rank.getScoreDifference() else 0
}
