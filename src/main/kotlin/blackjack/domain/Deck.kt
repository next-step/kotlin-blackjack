package blackjack.domain

class Deck private constructor() {
    private val cards: MutableList<Card> = mutableListOf()

    val quantity: Int
        get() = cards.size

    fun draw(): Card {
        check(cards.isNotEmpty()) { "카드가 없습니다." }
        return cards.removeFirst()
    }

    companion object {
        operator fun invoke(values: List<Card>): Deck = Deck().apply { cards += values }

        operator fun invoke(): Deck = Deck().apply { cards += generateShuffledCards() }

        private fun generateShuffledCards(): List<Card> = createDeck().shuffled()

        private fun createDeck(): List<Card> =
            Suit.entries.flatMap { suit -> createCardsForSuit(suit) }

        private fun createCardsForSuit(suit: Suit): List<Card> =
            Rank.entries.map { rank -> Card(suit, rank) }
    }
}
