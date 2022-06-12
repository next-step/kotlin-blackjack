package blackjack.domain.game

import blackjack.domain.player.Player

interface CardsByPlayerViewStrategy {
    
    fun print(player: Player, withScore: Boolean)
}
