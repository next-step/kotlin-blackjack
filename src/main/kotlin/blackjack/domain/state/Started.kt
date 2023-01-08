package blackjack.domain.state

import blackjack.domain.Hand

abstract class Started(open val hand: Hand) : State {
    override fun cards() = hand.cards
    override fun score() = hand.score
}
