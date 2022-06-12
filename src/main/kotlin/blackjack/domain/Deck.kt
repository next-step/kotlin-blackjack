package blackjack.domain

class Deck constructor(var cards: List<Card>) {

    fun shuffle() {
        cards = cards.shuffled()
    }

    companion object {
        fun create(): Deck {
            return Deck(
                Suit.values().map {
                    createDenominations(it)
                }.flatten()
            )
        }

        private fun createDenominations(suit: Suit): List<Card> {
            return Denomination.values().map {
                Card(suit, it)
            }
        }
    }
}
