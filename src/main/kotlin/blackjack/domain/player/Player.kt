package blackjack.domain.player

import blackjack.domain.card.Card

data class Player(
    val name: String,
    val hand: Hand,
    var state: PlayerState = PlayerState.Hit
) {
    init {
        if (hand.isBlackjack()) {
            state = PlayerState.Blackjack
        }
    }

    fun hit() {
        check(state == PlayerState.Hit) {
            "Invalid state transition: $state -> ${PlayerState.Hit}"
        }
        state = PlayerState.Hit
    }

    fun stay() {
        check(state == PlayerState.Hit)
        state = PlayerState.Stay
    }

    fun addCard(card: Card) {
        check(state == PlayerState.Hit) {
            "Invalid state transition: $state -> ${PlayerState.Hit}"
        }
        hand.addCard(card)
        state = if (hand.isBust()) {
            PlayerState.Bust
        } else if (hand.isBlackjack()) {
            PlayerState.Blackjack
        } else {
            PlayerState.Hit
        }
    }
}
