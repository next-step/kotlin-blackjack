package blackjack.domain.card

class Cards(private val _values: ArrayList<Card>) {
    val isBlackjack: Boolean
        get() = score().isBlackjack
    val isBust: Boolean
        get() = score().isBust
    val displayCards: List<String>
        get() = _values.map { it.displayName }

    fun score(): Score {
        var sum = Score(_values.map { it.score }.sum())
        val countOfAce = _values.count { it.isAce }
        repeat(countOfAce) {
            sum = sum.plusTenIfNotBust()
        }
        return sum
    }

    fun add(card: Card) {
        _values.add(card)
    }
}
