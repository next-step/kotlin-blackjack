package blackjack.domain

class CardDeck {
    val cards: List<Card> = setupCards()

    private fun setupCards(): List<Card> {
        return CardSymbol.values().flatMap { symbol ->
            CardNumber.NUMBER_RANGE.map { Card(symbol, CardNumber(it)) }
        }
    }
}
