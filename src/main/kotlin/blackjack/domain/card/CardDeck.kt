package blackjack.domain.card

class CardDeck {
    val cards: MutableList<Card> = CardNumber.values().flatMap { number ->
        CardSymbol.values().map { symbol ->
            Card(number, symbol)
        }
    }.toMutableList()

    fun peekCard(index: Int): Card {
        return cards.removeAt(index)
    }
}
