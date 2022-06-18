package blackjack.domain

class Deck(val cards: List<Card>) {

    private var index = 0

    fun draw(): Card {
        return draw(1).first()
    }

    fun draw(n: Int): List<Card> {
        require(index + n <= cards.size) { "Deck is Empty" }
        val subCards = cards.subList(index, index + n)
        index += n
        return subCards
    }

    companion object {
        fun create(): Deck {
            return Deck(
                Suit.values().map {
                    createDenominations(it)
                }.flatten().shuffled()
            )
        }

        private fun createDenominations(suit: Suit): List<Card> {
            return Denomination.values().map {
                Card(suit, it)
            }
        }
    }
}
