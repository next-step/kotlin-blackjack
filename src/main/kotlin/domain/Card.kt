package domain

data class Card(val suit: Suit, val value: CardValue) {
    override fun toString(): String {
        return "${value}${suit.description}"
    }
}
