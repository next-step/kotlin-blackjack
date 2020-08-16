package blackjack.model.card

class CardDeck {
    val cards: Cards = Cards.generate()

    fun pick(): Card = cards.draw()
}
