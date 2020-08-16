package blackjack.model

class CardDeck {
    private val cards: Cards = deckCreate()

    fun drawCard(): Card {
        return cards.draw().apply {
            cards.remove(this)
        }
    }

    fun shuffle() {
        cards.shuffle()
    }

    private fun deckCreate(): Cards {
        return Cards(
            Suit.values().flatMap { suit ->
                Denomination.values().map { denomination ->
                    Card(suit, denomination)
                }
            }
        )
    }
}
