package blackjack.domain

class CardDraw(val cards: Cards) {
    fun draw(): Card {
        return cards.getCard()
    }

    fun draw(size: Int): List<Card> {
        return List(size) { cards.getCard() }
    }

    companion object {
        const val INITIAL_SIZE = 2

        fun init(): CardDraw {
            val cards = Suit.values().flatMap { suit ->
                Denomination.values().map { denomination ->
                    Card(denomination, suit)
                }.shuffled()
            }
            return CardDraw(Cards.from(cards))
        }
    }
}
