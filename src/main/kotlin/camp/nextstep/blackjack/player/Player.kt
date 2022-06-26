package camp.nextstep.blackjack.player

import camp.nextstep.blackjack.card.Card

sealed interface Player {

    val name: String

    val hand: Hand

    fun receive(card: Card) {
        hand.add(card)
    }
}
