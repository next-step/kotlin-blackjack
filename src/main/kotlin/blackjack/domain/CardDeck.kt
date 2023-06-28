package blackjack.domain

class CardDeck(
    deck: List<Card> = cardDeck.shuffled()
) {
    private val deck: MutableList<Card> = deck.shuffled().toMutableList()
    fun getRandomCard(count: Int): MutableList<Card> {
        val randomCards = deck.shuffled().take(count).toMutableList()
        deck.removeAll(randomCards)
        return randomCards
    }

    companion object {
        private val cardDeck = CardType.values().flatMap { type ->
            NumberType.values().map {
                Card(it, type)
            }
        }
    }
}
