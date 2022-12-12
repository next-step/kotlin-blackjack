package blackjack.domain

class Cards(values: List<Card> = emptyList()) {
    private var _values: List<Card> = values.deepCopy()
    val values: List<Card>
        get() = _values.deepCopy()

    fun add(card: Card): Cards {
        return Cards(values + card)
    }

    fun pick(): Card {
        val mutableCards = _values.toMutableList()
        val card = mutableCards.removeFirst()
        _values = mutableCards.deepCopy()
        return card
    }

    fun getScore(): Int {
        val sum = values.sumOf { it.denomination.score }
        val hasAceCard = values.any { it.denomination == Denomination.ACE }

        return if (hasAceCard && (sum + ACE_BONUS_SCORE == WIN_SCORE)) WIN_SCORE
        else sum
    }

    override fun toString(): String {
        return values
            .joinToString(", ") { it.toString() }
    }

    private fun List<Card>.deepCopy(): List<Card> = map { it.copy() }

    companion object {
        private const val ACE_BONUS_SCORE = 10
        private const val WIN_SCORE = 21
    }
}
