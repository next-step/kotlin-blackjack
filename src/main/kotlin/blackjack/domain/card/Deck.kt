package blackjack.domain.card

class Deck {

    private val usedCards = mutableListOf<Card>()

    fun draw(): Card {
        val card = Card.generate()
        if (card !in usedCards) {
            usedCards.add(card)
            return card
        }
        return draw()
    }
}
