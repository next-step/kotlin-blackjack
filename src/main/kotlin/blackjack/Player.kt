package blackjack

import blackjack.state.Hit
import blackjack.state.PlayerState

class Player(
    private val name: String,
    private val hand: Cards = Cards(),
    private val state: PlayerState = Hit,
) {
    fun addCardToHand(card: Card): Player {
        return Player(name = this.name, hand = this.hand.add(card))
    }

    fun handSize(): Int {
        return this.hand.size()
    }

    fun isStart(): Boolean = this.isStart()
    fun isHit(): Boolean = this.isHit()
    fun isStay(): Boolean = this.isStay()
    fun isBust(): Boolean = this.isBust()
    fun isBlackjack(): Boolean = this.isBlackjack()
}
