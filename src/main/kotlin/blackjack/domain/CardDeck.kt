package blackjack.domain

class CardDeck {
    private var cards: List<Card>
    private var currentPosition: Int = 0

    init {
        val initCards: MutableList<Card> = mutableListOf()
        for (i in SuitType.values()) {
            initCards.addAll(createCardsForType(i))
        }
        cards = initCards.shuffled().toList()
    }

    private fun createCardsForType(suitType: SuitType): List<Card> {
        val cards: MutableList<Card> = mutableListOf()
        for (i in 2..10) {
            cards.add(Card(suitType, i.toString()))
        }
        cards.addAll(ValueType.values().map { Card(suitType, it.name) })
        return cards
    }

    fun pickCard(): Card {
        return cards[currentPosition++]
    }
}
