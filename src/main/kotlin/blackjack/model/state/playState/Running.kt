package blackjack.model.state.playState

import blackjack.model.state.State

abstract class Running : State {
    override fun isFinished(): Boolean {
        return false
    }
}
