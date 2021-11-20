package dto

import domain.player.Dealer
import domain.player.Player

class PlayerResultDto(player: Player, dealer: Dealer) : PlayerDto(player) {
    val win = toString(player.win(dealer))
    val profit = player.profit(dealer)

    private fun toString(win: Boolean) = if (win) "승" else "패"
}
