package blackjack.domain.game

import blackjack.domain.player.Player

interface Bet {
    fun getPlayerBetting(player: Player): Int
}
