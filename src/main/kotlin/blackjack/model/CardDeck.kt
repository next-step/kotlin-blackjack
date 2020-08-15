package blackjack.model

class CardDeck {
    private val cards: MutableList<Card> = init()

    fun pickCard(): Card {
        return cards.take(1).first().apply {
            cards.remove(this)
        }
    }

    private fun init(): MutableList<Card> {
        val cards = mutableListOf<Card>()
        Suit.values().forEach { type ->
            Denomination.values().forEach { value ->
                cards.add(Card(type, value))
            }
        }
        cards.shuffle()
        return cards
    }
}
