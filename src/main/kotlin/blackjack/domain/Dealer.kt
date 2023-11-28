package blackjack.domain

class Dealer(private val cardDeck: CardDeck) {
    private val cards = Cards(cardDeck.next(), cardDeck.next())

    val openedCards = cards.values[0]
    val hands
        get() = cards.values

    fun obtain() {
        cards.add(cardDeck.next())
    }

    fun isObtainable(): Boolean {
        return cards.sum() < 17
    }
}
