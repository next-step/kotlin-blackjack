package blackjack.domain.player

import blackjack.domain.card.Card

open class Player(val name: String) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card>
        get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }
}
