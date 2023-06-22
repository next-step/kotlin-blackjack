package domain.card

class Deck {
    private val cards: MutableList<BlackjackCard>
    val cardCount
        get() = cards.size

    init {
        cards = (1..DECK_SIZE)
            .map { makeDeck() }
            .flatten()
            .shuffled()
            .toMutableList()
    }

    fun issueCard(): BlackjackCard = cards.removeAt(0)

    private fun makeDeck(): List<BlackjackCard> {
        return Suit.values().map {
            makeCards(it, CardNumber.values())
        }.flatten()
    }

    private fun makeCards(suit: Suit, cardNumbers: Array<CardNumber>): List<BlackjackCard> {
        return cardNumbers.map { BlackjackCard(suit, it) }
    }

    companion object {
        private const val DECK_SIZE = 6
    }
}
