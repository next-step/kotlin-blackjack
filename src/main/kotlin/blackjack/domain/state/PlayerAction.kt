package blackjack.domain.state

import blackjack.domain.player.Player

interface PlayerAction {
    val isAbleToHit: Boolean

    val isAbleToStay: Boolean

    fun hit(player: Player): State

    fun stay(player: Player): State
}
