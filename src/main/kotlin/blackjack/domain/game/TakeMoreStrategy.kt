package blackjack.domain.game

import blackjack.domain.player.Player

interface TakeMoreStrategy {
    fun wantToTake(player: Player): Boolean
}
