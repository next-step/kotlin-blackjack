package blackjack.domain.card

data class PlayingCard(val suit: Suit, val denomination: Denomination) {
    override fun toString(): String {
        return denomination.toString() + suit.toString()
    }

    fun score(): Int {
        return this.denomination.score
    }
}
