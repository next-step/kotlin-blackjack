package blackjack.model.card

const val BOTTOM_CARD_NUMBER = 0

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

    fun pick(): Card {
        val card = cards[BOTTOM_CARD_NUMBER]

        cards.removeAt(BOTTOM_CARD_NUMBER)
        return card
    }
}
