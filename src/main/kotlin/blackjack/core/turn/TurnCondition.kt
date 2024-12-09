package blackjack.core.turn

import blackjack.core.player.Player

interface TurnCondition {
    fun canGo(player: Player): Boolean
}
