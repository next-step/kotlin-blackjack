package blackjack.model.card

class CardDeck {
    private val _cards: MutableList<Card> = generate()

    fun pick(): Card = draw()

    private fun draw(): Card {
        val drawCard = _cards[BOTTOM_CARD_NUMBER]
        _cards.remove(drawCard)

        return drawCard.copy()
    }

    companion object {
        private const val BOTTOM_CARD_NUMBER = 0

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
}
