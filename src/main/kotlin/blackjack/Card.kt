package blackjack

data class Card(val number: CardNumber, val suit: Suit) : Comparable<Card> {
    fun number(): Int {
        return number.baseValue
    }

    override fun compareTo(other: Card): Int = number compareTo other.number
}
