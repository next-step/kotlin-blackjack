package blackjack

data class Card(val suit: Suit, val denomination: Denomination) {
    fun number(): Int {
        return denomination.score
    }
}
