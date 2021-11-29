package blackJack.domain.result

import blackJack.domain.player.Dealer
import blackJack.domain.player.Player

enum class WinDrawLose {
    WIN, DRAW, LOSE;

    companion object {
        fun from(player: Player, dealer: Dealer): WinDrawLose =
            when {
                dealer.isBustPlayer() || playerScoreGraterThenDealerScore(player, dealer) -> WIN
                playerScoreEqualDealerScore(player, dealer) -> DRAW
                player.isBustPlayer() || dealerScoreGraterThenPlayerScore(dealer, player) -> LOSE
                else -> DRAW
            }

        private fun playerScoreGraterThenDealerScore(player: Player, dealer: Dealer) =
            !player.isBustPlayer() && (player.getScore() > dealer.getScore())

        private fun playerScoreEqualDealerScore(player: Player, dealer: Dealer) =
            player.getScore() == dealer.getScore()

        private fun dealerScoreGraterThenPlayerScore(dealer: Dealer, player: Player) =
            !dealer.isBustPlayer() && (dealer.getScore() > player.getScore())
    }
}
