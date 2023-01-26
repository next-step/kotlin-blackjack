package blackjack.domain

data class Card(
    val cardSuit: CardSuit,
    val denomination: Denomination,
) {
    override fun toString(): String {
        return denomination.description + cardSuit.description
    }

    fun isAce(): Boolean {
        return denomination == Denomination.ACE
    }
}
