package blackjack.domain.game

import blackjack.domain.player.Player

interface Turn {
    fun isPlayerTurnOff(player: Player): Boolean
}
