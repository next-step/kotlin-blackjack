package blackjack.model

class CardDeck {
    var cards: List<Card> = INIT_CARDS
        private set

    fun drawCard(): Card {
        val shuffledCards = cards.shuffled()
        val pickedCard = shuffledCards.first()
        cards = shuffledCards.drop(1)

        return pickedCard
    }

    companion object {
        val INIT_CARDS = CardValue.values().map { value ->
            CardSuit.values().map { suit ->
                Card(value, suit)
            }
        }.flatten()
    }
}
