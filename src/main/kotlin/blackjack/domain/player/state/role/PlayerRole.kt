package blackjack.domain.player.state.role

import blackjack.domain.card.state.State

abstract class PlayerRole : Role {
    lateinit var state: State

    override fun isDealer(): Boolean {
        return false
    }
}
