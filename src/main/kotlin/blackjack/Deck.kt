package blackjack

class Deck(cards: List<Card> = Cards.getCards()) {
    private val _cards: MutableList<Card> = cards.toMutableList()

    fun deal(cardShape: CardShape, cardNumber: CardNumber): Card {
        val card = Card(cardShape, cardNumber)
        _cards.remove(card)
        return card
    }

    val cards: List<Card>
        get() = _cards.toList()
}
