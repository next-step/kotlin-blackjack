package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.card.Card

abstract class State(open val hand: Hand) {
    val score: Int
        get() = hand.score

    abstract fun draw(card: Card): State
}
