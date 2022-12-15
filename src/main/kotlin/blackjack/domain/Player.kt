package blackjack.domain

import blackjack.domain.card.Card

class Player(val name: String, cards: List<Card> = emptyList()) {
    private val _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    fun enroll(card: Card) {
        _cards.add(card)
    }
}
