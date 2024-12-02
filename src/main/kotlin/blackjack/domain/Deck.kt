package blackjack.domain

class Deck(
    private val cards: MutableList<Card> = mutableListOf(),
) {
    val quantity: Int
        get() = cards.size
    
    init {
        generateShuffledCards().also(cards::addAll)
    }

    private fun generateShuffledCards(): List<Card> {
        return createDeck().shuffled()
    }

    private fun createDeck(): List<Card> {
        return Suit.entries.flatMap { suit -> createCardsForSuit(suit) }
    }

    private fun createCardsForSuit(suit: Suit): List<Card> {
        return Rank.entries.map { rank -> Card(suit, rank) }
    }

    fun draw(): Card {
        check(quantity != 0) { "카드가 모두 소진되었습니다." }

        return cards.removeFirst()
    }
}
