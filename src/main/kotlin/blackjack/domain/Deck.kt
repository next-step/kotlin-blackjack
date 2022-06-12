package blackjack.domain

class Deck private constructor(var cards: List<Card>) {

    companion object {
        fun create(): Deck {
            return Deck(Suit.values().map {
                createDenominations(it)
            }.flatten())
        }

        private fun createDenominations(suit: Suit): List<Card> {
            return Denomination.values().map {
                Card(suit, it)
            }
        }
    }
}
