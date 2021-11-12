package blackjack.domain.deck

class Card(
    private val denomination: Denomination,
    private val suit: Suit,
) {
    override fun toString(): String = denomination.symbol + suit.value

    fun getScore(): Int {
        return denomination.score
    }
}
