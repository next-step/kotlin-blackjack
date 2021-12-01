package blackjack.domain.card

data class Card(val suit: Suit, val denomination: Denomination) {

    fun score(): Score = denomination.score

    fun isAce(): Boolean = (denomination.isAce())
}
