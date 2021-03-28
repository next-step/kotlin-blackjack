package blackjack.domain.card

class Card private constructor(private val suit: Suit, private val denomination: Denomination) {
    val isAce: Boolean
        get() = denomination.isAce

    val score: Int
        get() = denomination.score

    companion object {
        private val CARDS: MutableMap<String, Card> = HashMap()

        fun of(suit: Suit, denomination: Denomination): Card? {
            return CARDS[toKey(
                suit,
                denomination
            )]
        }

        private fun toKey(suit: Suit, denomination: Denomination): String {
            return suit.name + denomination.name
        }

        init {
            for(suit in Suit.values()) {
                for(denomination in Denomination.values()) {
                    CARDS[toKey(
                        suit,
                        denomination
                    )] = Card(suit, denomination)
                }
            }
        }
    }
}