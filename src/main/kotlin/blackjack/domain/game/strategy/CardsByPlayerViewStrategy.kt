package blackjack.domain.game.strategy

import blackjack.domain.player.Player

interface CardsByPlayerViewStrategy {

    fun print(player: Player, withScore: Boolean)
}
