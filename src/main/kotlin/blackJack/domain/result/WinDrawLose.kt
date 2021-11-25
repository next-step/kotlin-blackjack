package blackJack.domain.result

import blackJack.domain.player.Dealer
import blackJack.domain.player.Player

enum class WinDrawLose {
    WIN, DRAW, LOSE;

    companion object {
        fun from(player: Player, dealer: Dealer): WinDrawLose =
            when {
                dealer.isBustPlayer() || !player.isBustPlayer() && player.getScore() > dealer.getScore() -> WIN
                player.getScore() == dealer.getScore() -> DRAW
                else -> LOSE
            }
    }
}
