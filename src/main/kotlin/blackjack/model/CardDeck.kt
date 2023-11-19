package blackjack.model

class CardDeck {
    private var cards: List<Card> = initCards()

    fun drawCard(): Card {
        val pickedCard = cards.shuffled().toMutableList()[0];
        cards = cards.filter { it != pickedCard }
        return pickedCard
    }

    private fun initCards() = CardValue.VALUES.map { value ->
        CardSuit.SUITS.map { suit ->
            Card(CardValue.from(value), CardSuit.from(suit))
        }
    }.flatten()
}
