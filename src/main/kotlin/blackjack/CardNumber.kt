package blackjack

sealed class CardNumber(val face: String, val baseValue: Int) : Comparable<CardNumber> {
    data object Ace : CardNumber("A", 1)

    data object Jack : CardNumber("J", 10)

    data object Queen : CardNumber("Q", 10)

    data object King : CardNumber("K", 10)

    override fun compareTo(other: CardNumber): Int {
        return this.baseValue compareTo other.baseValue
    }
}

data class Number(private val value: Int) : CardNumber(value.toString(), value)
