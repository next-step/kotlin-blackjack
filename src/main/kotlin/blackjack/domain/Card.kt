package blackjack.domain

data class Card(
    val cardSuit: CardSuit,
    val denomination: Denomination,
) {
    override fun toString(): String {
        return denomination.description + cardSuit.description
    }

    fun calculate(score: Int): Int {
        return denomination.calc(score)
    }

    fun isAce(): Boolean {
        return denomination == Denomination.ACE_1 || denomination == Denomination.ACE_10
    }
}
