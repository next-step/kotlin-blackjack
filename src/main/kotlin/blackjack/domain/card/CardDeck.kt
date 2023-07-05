package blackjack.domain.card

interface CardDeck {
    fun fetch(): Card
    fun countOfCards(): Int
}

class ShuffledCardDeck: CardDeck {
    private val cards: MutableList<Card>
    init {
        val cardSuits = CardSuit.values().toList()
        val cardNumbers = CardNumber.values().toList()
        cards = cardSuits
            .map { cardShape -> cardNumbers.map { cardNumber -> Card(cardShape, cardNumber) } }
            .flatten()
            .toSet()
            .shuffled()
            .toMutableList()
    }

    override fun fetch(): Card {
        if (cards.isEmpty()) throw RuntimeException()
        return cards.removeFirst()
    }

    override fun countOfCards(): Int {
        return cards.size
    }
}
