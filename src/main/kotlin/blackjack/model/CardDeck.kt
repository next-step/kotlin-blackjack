package blackjack.model

class CardDeck {
    var cards: List<Card> = INIT_CARDS
        private set

    fun drawCard(): Card {
        val pickedCard = cards.shuffled().toMutableList()[0];
        cards = cards.filter { it != pickedCard }
        return pickedCard
    }

    companion object {
        val INIT_CARDS = CardValue.VALUES.map { value ->
            CardSuit.values().map { suit ->
                Card(CardValue.from(value), suit)
            }
        }.flatten()
    }
}
