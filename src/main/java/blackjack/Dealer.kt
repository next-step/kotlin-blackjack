package blackjack

import blackjack.card.Card

class Dealer(cards: List<Card>) {

    private val _cards: MutableList<Card> = cards.toMutableList()

    val cardCount: Int
        get() = _cards.size

    init {
        _cards.shuffle()
    }

    fun giveTwoCards(): List<Card> {
        return (0..1).map { giveCard() }
    }

    fun giveCard(): Card {
        return _cards.removeAt(0)
    }

}
