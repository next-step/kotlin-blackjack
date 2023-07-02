package blackjack.domain.card

object CardFactory {
    val defaultCards = initCards().shuffled()

    private fun initCards(): List<Card> {
        val cards = mutableListOf<Card>()
        for (suit in Suit.values()) {
            createCards(cards, suit)
        }
        return cards
    }

    private fun createCards(cards: MutableList<Card>, suit: Suit) {
        for (denomination in Denomination.values()) {
            cards.add(Card(denomination, suit))
        }
    }
}
