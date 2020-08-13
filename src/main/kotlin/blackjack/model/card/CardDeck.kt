package blackjack.model.card

class CardDeck {
    val cards: Cards = generate()

    fun generate(): Cards {
        val cards = mutableListOf<Card>()

        for (suit in Card.Suit.values()) {
            for (denomination in Card.Denomination.values()) {
                cards.add(Card(suit, denomination))
            }
        }
        return Cards(cards.shuffled() as MutableList<Card>)
    }

    fun pick(): Card = cards.draw()
}
