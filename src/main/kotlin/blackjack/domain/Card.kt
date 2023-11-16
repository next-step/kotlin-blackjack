package blackjack.domain

data class Card(
    val denomination: Denomination,
    val suit: Suit,
) {
    fun isAce(): Boolean {
        return denomination == Denomination.ACE
    }
}
