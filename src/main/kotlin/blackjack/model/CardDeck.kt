package blackjack.model

class CardDeck {
    private var cards: List<Card> = INIT_CARDS

    fun drawCard(): Card {
        val pickedCard = cards.shuffled().toMutableList()[0];
        cards = cards.filter { it != pickedCard }
        return pickedCard
    }


    companion object {
        val INIT_CARDS = CardValue.VALUES.map { value ->
            CardSuit.SUITS.map { suit ->
                Card(CardValue.from(value), CardSuit.from(suit))
            }
        }.flatten()
    }
}
