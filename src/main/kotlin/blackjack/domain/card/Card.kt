package blackjack.domain.card

data class Card(val suit: Suit, val denomination: Denomination) {
    fun score(): Score = denomination.score
    fun hasAce(): Boolean = (denomination == Denomination.ACE)
}
