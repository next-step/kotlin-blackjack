package blackjack.domain.player

import blackjack.domain.deck.Card

class Hand {
    private val cards = arrayListOf<Card>()

    fun getCards(): List<Card> = cards.toList()

    fun addNew(card: Card) {
        cards.add(card)
    }
}
