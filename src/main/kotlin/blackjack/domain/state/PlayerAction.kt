package blackjack.domain.state

import blackjack.domain.player.Player

interface PlayerAction {
    fun hit(player: Player): State

    fun stay(player: Player): State
}
