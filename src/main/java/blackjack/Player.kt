package blackjack

import blackjack.card.Card

class Player(val name: String, cards: List<Card>) {

    private val _cards: MutableList<Card> = cards.toMutableList()

    val cards: List<Card>
        get() = _cards.toList()

    fun receiveCard(card: Card) {
        _cards.add(card)
    }

    fun getTotalSum(): Int {
        return BlackjackRule.getTotalSum(_cards)
    }

    fun isReceiveMoreCard(): Boolean {
        return BlackjackRule.isReceiveMoreCard(getTotalSum())
    }

}
