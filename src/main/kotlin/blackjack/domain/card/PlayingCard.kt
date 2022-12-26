package blackjack.domain.card

fun SpadeCard(denomination: Denomination): PlayingCard = PlayingCard(Suit.SPADES, denomination)

data class PlayingCard(private val suit: Suit, private val denomination: Denomination) {
    override fun toString(): String {
        return denomination.toString() + suit.symbol
    }

    fun score(): Int {
        return this.denomination.score
    }

    fun isAce(): Boolean {
        return this.denomination == Denomination.ACE
    }
}
