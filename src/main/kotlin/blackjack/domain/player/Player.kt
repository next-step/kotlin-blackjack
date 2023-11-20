package blackjack.domain.player

import blackjack.model.Card
import java.lang.RuntimeException

data class Player(
    val name: String,
    val hand: Hand,
    var state: PlayerState = PlayerState.Hit
) {
    init {
        if (hand.isBlackjack()) {
            state = PlayerState.Blackjack
        } else if (hand.isBust()) {
            state = PlayerState.Bust
        }
    }
    fun hit() {
        require(state == PlayerState.Hit) {
            RuntimeException("Invalid state transition: $state -> ${PlayerState.Hit}")
        }
        state = PlayerState.Hit
    }

    fun stay() {
        require(state == PlayerState.Hit)
        state = PlayerState.Stay
    }

    fun addCard(card: Card) {
        require(state == PlayerState.Hit) {
            RuntimeException("Invalid state transition: $state -> ${PlayerState.Hit}")
        }
        hand.addCard(card)
        state = if (hand.isBust()) {
            PlayerState.Bust
        } else {
            PlayerState.Hit
        }
    }
}
