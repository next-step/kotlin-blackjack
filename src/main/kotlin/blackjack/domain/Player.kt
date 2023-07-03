package blackjack.domain

import blackjack.domain.card.Card

data class Player(val name: String, val hand: PlayerHand = PlayerHand.init) {
    fun canDraw(): Boolean = hand.canAdd()

    fun addCard(card: Card): Player {
        val newState = this.hand.add(card)
        return copy(hand = newState)
    }

    fun addCards(cards: List<Card>): Player {
        val newState = this.hand.add(cards)
        return copy(hand = newState)
    }

    fun cards() = hand.cards

    fun score() = hand.score
}
