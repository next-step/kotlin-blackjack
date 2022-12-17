package blackjack.domain

import blackjack.model.Card

class Dealer(
    private val _deck: CardDeck = CardDeckImpl(),
) {
    val deck: CardDeck
        get() = _deck

    fun shuffle() = _deck.shuffle()

    fun deliverCard(): Card = _deck.takeOutFirstCard()
}
