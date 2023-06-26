package blackjack.domain

import blackjack.domain.Cards.Companion.EMPTY_CARDS

class Player(val name: String) {

    private var _cards: Cards = EMPTY_CARDS
    val cards: Cards
        get() = _cards

    fun receiveCard(card: Card): Cards {
        addCard(card)
        return cards
    }

    private fun addCard(card: Card) {
        _cards = _cards.addCard(card)
    }
}
