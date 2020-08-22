package blackJack.domain

class Deck() {
    var cards = DEFAULT_DECK
        private set

    fun shuffle(shuffleDeck: (cards: List<Card>) -> List<Card>) {
        cards = shuffleDeck(cards)
    }

    fun getCard(): Card {
        val card = cards[0]
        deleteCard()
        return card
    }

    private fun deleteCard() {
        cards = cards.drop(1)
    }

    companion object {
        val DEFAULT_DECK = Shape.values().flatMap { shape -> Denomination.values().map { Card(shape, it) } }
    }
}
