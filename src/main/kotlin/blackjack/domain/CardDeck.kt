package blackjack.domain

object CardDeck : Deck {
    val cards = mutableListOf<Card>()

    override fun fillDeck(newCards: List<Card>) {
        cards.clear()
        newCards.forEach { cards.add(it) }
        cards.shuffle()
    }

    override fun drawCard(): Card {
        return cards.removeFirst()
    }
}
