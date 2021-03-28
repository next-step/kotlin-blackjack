package blackjack.domain.card

class Cards(cards: List<Card>) {
    val isBlackjack: Boolean
        get() = score().isBlackjack
    val isBust: Boolean
        get() = score().isBust

    private val _values: MutableList<Card> = cards.toMutableList()
    val values: List<Card>
        get() = _values


    fun score(): Score {
        var sum = Score(values.map { it.score }.sum())
        val countOfAce = values.count { it.isAce }
        repeat(countOfAce) {
            sum = sum.plusTenIfNotBust()
        }
        return sum
    }

    fun add(card: Card) {
        _values.add(card)
    }
}
