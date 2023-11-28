package blackjack.domain

class Dealer(cardDeck: CardDeck) {
    private val cards = Cards(cardDeck.next(), cardDeck.next())
    val openedCards = cards.values[0]
}
