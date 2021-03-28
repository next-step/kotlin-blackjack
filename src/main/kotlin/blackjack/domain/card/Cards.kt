package blackjack.domain.card

class Cards(private val values: List<Card>) {
    val isBlackjack: Boolean
        get() = score().isBlackjack
    val isBust: Boolean
        get() = score().isBust

    fun score(): Score {
        var sum = Score(values.map { it.score }.sum())
        val countOfAce = values.count { it.isAce }
        repeat(countOfAce) {
            sum = sum.plusTenIfNotBust()
        }
        return sum
    }

    fun add(card: Card): Cards {
        val values = values.toMutableList()
        values.add(card)
        return Cards(values)
    }
}
