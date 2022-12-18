package blackjack.domain

class Cards(values: List<Card> = emptyList()) {
    private var _values: MutableList<Card> = values.toMutableList()
    val values: List<Card>
        get() = _values.toList()

    fun add(card: Card) {
        _values.add(card)
    }

    fun pick() = _values.removeFirst()

    fun getScore(): Int {
        val sum = values.sumOf { it.denomination.score }
        val hasAceCard = values.any { it.denomination == Denomination.ACE }

        return if (hasAceCard && (sum + ACE_BONUS_SCORE <= WIN_SCORE)) sum + ACE_BONUS_SCORE
        else sum
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
