package blackjack.domain

class Deck(val cards: Cards) {
    fun draw(): Card {
        return cards.dealCard()
    }

    fun firstDraw(): List<Card> {
        return List(INITIAL_SIZE) { cards.dealCard() }
    }

    companion object {
        const val INITIAL_SIZE = 2

        fun init(): Deck {
            val cards = Suit.values().flatMap { suit ->
                Denomination.values().map { denomination ->
                    Card(denomination, suit)
                }.shuffled()
            }
            return Deck(Cards.from(cards))
        }
    }
}
