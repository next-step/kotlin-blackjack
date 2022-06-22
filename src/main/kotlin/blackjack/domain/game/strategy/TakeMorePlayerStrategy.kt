package blackjack.domain.game.strategy

import blackjack.domain.player.Player

interface TakeMorePlayerStrategy {
    fun wantToTake(player: Player): Boolean = false
}
