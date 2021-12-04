package blackjack.domain

data class Card(val suit: Suit, val denomination: Denomination) {
    override fun toString(): String {
        return "${denomination.shape}${suit.shape}"
    }
}
