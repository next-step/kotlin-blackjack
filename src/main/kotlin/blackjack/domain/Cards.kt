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
        return Denomination.sum(values.map { it.denomination })
    }

    override fun toString(): String {
        return values
            .joinToString(", ") { it.toString() }
    }

    private fun List<Card>.deepCopy(): List<Card> = map { it.copy() }
}
