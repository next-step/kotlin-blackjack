package blackjack.domain

class Cards(values: List<Card> = emptyList()) {
    private var _values: MutableList<Card> = values.toMutableList()
    val values: List<Card>
        get() = _values.toList()

    fun add(card: Card) {
        _values.add(card)
    }

    fun pick(): Card {
        return _values.removeFirst()
    }

    fun getScore(): Int {
        val sum = values.sumOf { it.denomination.score }

        return if (hasAce() && (sum + ACE_BONUS_SCORE <= WIN_SCORE)) sum + ACE_BONUS_SCORE
        else sum
    }

    fun isBlackJack(): Boolean {
        return getScore() == WIN_SCORE && hasAce()
    }

    private fun hasAce(): Boolean {
        return values.any { it.denomination == Denomination.ACE }
    }

    override fun toString(): String {
        return values
            .joinToString(", ") { it.toString() }
    }

    companion object {
        private const val ACE_BONUS_SCORE = 10
        private const val WIN_SCORE = 21
    }
}
