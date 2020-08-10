package blackjack.domain

class CardDeck {
    private var cards: List<Card>
    private var currentPosition: Int = 0

    init {
        val initCards: MutableList<Card> = mutableListOf()
        SuitType.values().map { initCards.addAll(createCardsForType(it)) }
        cards = initCards.shuffled().toList()
    }

    private fun createCardsForType(suitType: SuitType): List<Card> {
        return ValueType.values().map { Card(suitType, it) }
    }

    fun pickCard(): Card {
        return cards[currentPosition++]
    }
}
