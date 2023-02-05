package blackjack.domain.card

data class PlayingCard(private val suit: Suit, private val denomination: Denomination) {
    val score: Int
        get() = denomination.score

    val isAce: Boolean
        get() = denomination == Denomination.ACE

    override fun toString(): String = denomination.toString() + suit.symbol
}
