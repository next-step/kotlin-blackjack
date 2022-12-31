package blackjack.domain

class Card private constructor(
    val denomination: Denomination,
    private val suit: Suit
) {

    val score = denomination.score

    override fun toString(): String {
        return "${denomination.score}${suit.symbol}"
    }

    companion object {
        fun of(
            denomination: Denomination = Denomination.random(),
            suit: Suit = Suit.random()
        ): Card {
            return Card(denomination, suit)
        }
    }
}
