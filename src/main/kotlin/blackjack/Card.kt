package blackjack

data class Card(val number: CardNumber, val suit: Suit) {
    fun number(): Int {
        return number.baseValue
    }
}
