package blackjack.domain

import blackjack.domain.Card.Companion.CARD_DECK

class Dealer(deck: Cards = Cards(CARD_DECK)) {
    private var _list: MutableList<Card> = deck.list.toMutableList()

    val deck: Cards
        get() = Cards(_list.toList())

    fun divide(): Card = _list.removeFirst()

    fun shuffle() {
        _list = _list.shuffled().toMutableList()
    }
}
