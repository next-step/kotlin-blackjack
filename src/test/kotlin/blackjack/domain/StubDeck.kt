package blackjack.domain

class StubDeck(
    cards: List<Card>,
) : Deck {
    private val iterator = cards.iterator()

    override val size: Int
        get() = throw UnsupportedOperationException()

    override fun draw(): Card = iterator.next()

    companion object {
        val DUMMY_SUIT = Suit.SPADES

        fun from(vararg ranks: Rank): StubDeck = StubDeck(ranks.map { Card(DUMMY_SUIT, it) })
    }
}
