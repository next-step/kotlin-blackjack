package blackjack.model

class Deck {
    private val allCards: MutableSet<Card> = generateAllCards().toMutableSet()

    private fun generateAllCards(): Set<Card> {
        val cards = mutableSetOf<Card>()
        for (suit in Suit.list) {
            for (rank in Rank.list) {
                cards.add(Card.createCard(suit, rank))
            }
        }
        return cards
    }

    fun takeRandomCard(): Card {
        require(allCards.isNotEmpty()) { "남는 카드가 없습니다." }
        val card = allCards.random()
        allCards.remove(card)
        return card
    }

    fun resetAllCards() {
        allCards.clear()
        allCards.addAll(generateAllCards())
    }

    fun cardsRemaining(): Int = allCards.size
}
