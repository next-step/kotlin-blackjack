package blackjack.domain

import blackjack.domain.card.Card

class Player(val name: String, val hand: PlayerHand = PlayerHand.init) {
    fun canHit(): Boolean = hand.isBust().not()

    fun addCard(card: Card): Player {
        val newState = this.hand.add(card)
        return Player(name = name, hand = newState)
    }

    fun addCards(cards: List<Card>): Player {
        val newState = this.hand.add(cards)
        return Player(name = name, hand = newState)
    }

    fun cards() = hand.cards

    fun score() = hand.score
}
