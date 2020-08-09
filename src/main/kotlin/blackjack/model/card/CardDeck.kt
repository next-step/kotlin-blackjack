package blackjack.model.card

class CardDeck {
    var cards: MutableList<Card> = generate()

    fun generate(): MutableList<Card> {
        val cards = mutableListOf<Card>()

        for (suit in Card.Suit.values()) {
            for (denomination in Card.Denomination.values()) {
                cards.add(Card(suit, denomination))
            }
        }
        return cards.shuffled() as MutableList<Card>
    }
}
