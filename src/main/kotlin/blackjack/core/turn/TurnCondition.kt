package blackjack.core.turn

import blackjack.core.player.Player

interface TurnCondition {
    fun carGo(player: Player): Boolean
}
