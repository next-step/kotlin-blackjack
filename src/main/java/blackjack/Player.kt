package blackjack

import blackjack.card.Card

class Player(val name: String, cards: List<Card>) {

    private val _cards: MutableList<Card> = cards.toMutableList()

    val cards: List<Card>
        get() = _cards

    fun receiveCard(card: Card) {
        _cards.add(card)
    }
}
