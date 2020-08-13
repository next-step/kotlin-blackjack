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
        (ACE..KING).map { _cards.add(shape.makeCard(it)) }
    }

    fun shuffle() {
        cards = _cards.shuffled()
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
        private const val ACE = 1
        private const val KING = 13
    }
}
