package blackjack

class Cards(values: List<Card> = emptyList()) {
    private var _values: List<Card> = values.deepCopy()
    val values: List<Card>
        get() = _values.deepCopy()

    fun add(card: Card): Cards {
        return Cards(listOf(card) + values)
    }

    fun pick(): Card {
        val shuffledCards = values.shuffled().toMutableList()
        val card = shuffledCards.removeFirst()
        shuffledCards.remove(card)
        _values = shuffledCards.deepCopy()
        return card
    }

    fun getScore(): Int {
        return Denomination.sum(values.map { it.denomination })
    }

    private fun List<Card>.deepCopy(): List<Card> = map { it.copy() }
}
