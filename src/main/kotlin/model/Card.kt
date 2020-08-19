package model

class Card(val suit: Suit, val denomination: Denomination) {
    override fun toString(): String {
        return "${denomination.score}${suit.displayName}"
    }
}
