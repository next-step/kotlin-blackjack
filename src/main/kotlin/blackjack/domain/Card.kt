package blackjack.domain

data class Card(val shape: Shape, val denomination: Denomination) {
    override fun toString(): String {
        return "$denomination$shape"
    }
}
