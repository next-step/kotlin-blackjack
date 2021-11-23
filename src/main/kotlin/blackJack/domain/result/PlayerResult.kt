package blackJack.domain.result

import blackJack.domain.player.Dealer
import blackJack.domain.player.Player

class PlayerResult(
    val name: String,
    val winDrawLose: WinDrawLose
) {

    companion object {
        fun winOrLose(player: Player, dealer: Dealer): PlayerResult =
            PlayerResult(
                player.name, winDrawLose(player, dealer)
            )

        private fun winDrawLose(player: Player, dealer: Dealer): WinDrawLose =
            when {
                dealer.isBustPlayer() || !player.isBustPlayer() && player.getScore() > dealer.getScore() -> WinDrawLose.WIN
                player.getScore() == dealer.getScore() -> WinDrawLose.DRAW
                else -> WinDrawLose.LOSE
            }
    }
}
