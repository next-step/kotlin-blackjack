package blackjack.domain

class CardDeck(
    val deck: MutableList<Card> = cardDeck.shuffled().toMutableList()
) {
    fun getRandomCard(count: Int): MutableList<Card> {
        val randomCards = deck.shuffled().take(count).toMutableList()
        deck.removeAll(randomCards)
        return randomCards
    }

    companion object {
        val cardDeck = CardType.values().flatMap { type ->
            NumberType.values().map {
                Card(it, type)
            }
        }
    }
}
