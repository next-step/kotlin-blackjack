package blackjack.domain.card

class CardDeck {
    val cards: Cards = getShuffledCards()

    private fun getShuffledCards(): Cards {
        val cards = CardShape.values().flatMap { cardShape ->
            CardNumber.values().map { cardNumber ->
                Card(cardShape, cardNumber)
            }
        }.toMutableList()
        return Cards(cards.apply { this.shuffle() })
    }
}
