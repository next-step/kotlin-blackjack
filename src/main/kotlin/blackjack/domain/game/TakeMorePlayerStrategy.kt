package blackjack.domain.game

import blackjack.domain.player.Player

interface TakeMorePlayerStrategy {
    fun wantToTake(player: Player): Boolean = false
    fun canBeTakeOneCard(score: Int): Boolean = false
}
