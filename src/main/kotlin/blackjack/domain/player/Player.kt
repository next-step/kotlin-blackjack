package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.state.Running
import blackjack.domain.state.State

abstract class Player(open val name: String, open val state: State) {
    abstract fun draw(card: Card): Player
    abstract fun stay(): Player
    fun canDraw() = state is Running
    fun score() = state.score()
}
