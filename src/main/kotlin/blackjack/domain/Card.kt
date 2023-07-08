package blackjack.domain

class Card(val suit: Suit, val denomination: Denomination) {
    override fun toString(): String {
        return "${denomination.symbol}${suit.alias}"
    }
}
