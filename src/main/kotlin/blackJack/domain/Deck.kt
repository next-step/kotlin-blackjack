package blackJack.domain

class Deck() {
    private val _cards = mutableListOf<Card>()
    var cards = makeDeck()
        private set

    private fun makeDeck(): List<Card> {
        Shape.values().forEach { makeCard(it) }
        return _cards
    }

    private fun makeCard(shape: Shape) {
        Denomination.values().forEach { _cards.add(Card(shape, it)) }
    }

    fun shuffle(shuffleDeck: (cards: List<Card>) -> List<Card>) {
        cards = shuffleDeck(_cards)
    }

    fun getCard(): Card {
        val card = cards[0]
        deleteCard()
        return card
    }

    private fun deleteCard() {
        cards = cards.drop(1)
    }
}
