package blackjack.domain.cards

import blackjack.domain.card.Card

abstract class Cards(private val _cardList: MutableList<Card>) {
    val cardList get() = _cardList.toList()
    val size get() = _cardList.size

    fun add(card: Card) {
        _cardList.add(card)
    }

    override fun toString(): String = _cardList.toString()
}
