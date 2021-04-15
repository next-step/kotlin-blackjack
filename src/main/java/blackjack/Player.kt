package blackjack

import blackjack.card.Card

open class Player(val name: String) {

    private val _cards: MutableList<Card> = mutableListOf()

    val cards: List<Card>
        get() = _cards.toList()

    fun receiveCard(card: Card) {
        _cards.add(card)
    }

    fun receiveCards(cards: List<Card>) {
        _cards.addAll(cards)
    }

    fun getTotalSum(): Int {
        return BlackjackRule.getTotalSum(_cards)
    }

    open fun isReceiveMoreCard(): Boolean {
        return BlackjackRule.isReceiveMoreCard(getTotalSum())
    }
}
