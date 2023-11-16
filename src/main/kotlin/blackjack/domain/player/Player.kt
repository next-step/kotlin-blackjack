package blackjack.domain.player

import blackjack.domain.card.Card
import java.lang.RuntimeException

data class Player(
    val hand: Hand,
    var state: PlayerState = PlayerState.Idle
) {
    init {
        if (hand.isBlackjack()) {
            state = PlayerState.Blackjack
        } else if (hand.isBust()) {
            state = PlayerState.Bust
        }
    }
    fun hit() {
        require(state == PlayerState.Idle) {
            RuntimeException("Invalid state transition: $state -> ${PlayerState.Hit}")
        }
        state = PlayerState.Hit
    }

    fun stay() {
        require(state == PlayerState.Idle)
        state = PlayerState.Stay
    }

    fun addCard(card: Card) {
        require(state == PlayerState.Hit) {
            RuntimeException("Invalid state transition: $state -> ${PlayerState.Hit}")
        }
        hand.addCard(card)
        if (hand.isBust()) {
            state = PlayerState.Bust
        }

        if (hand.isBlackjack()) {
            state = PlayerState.Blackjack
        }
    }
}
