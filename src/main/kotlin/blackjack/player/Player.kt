package blackjack.player

import blackjack.card.Card

class Player(
    val name: String,
    private val hand: Hand = Hand()
) {
    fun addCard(card: Card) {
        hand.addCard(card)
    }
}
