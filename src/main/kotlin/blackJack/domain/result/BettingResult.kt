package blackJack.domain.result

import blackJack.domain.player.Dealer
import blackJack.domain.player.Player

class BettingResult(val profit: Int) {

    companion object {
        fun of(player: Player, dealer: Dealer, winDrawLose: WinDrawLose): BettingResult {
            if (player.isBlackJackPlayer() && !dealer.isBlackJackPlayer()) {
                return BettingResult((player.getBettingMoney() * TIMES).toInt())
            }
            return when (winDrawLose) {
                WinDrawLose.WIN -> player.getBettingMoney()
                WinDrawLose.DRAW -> NOT_PROFIT
                WinDrawLose.LOSE -> -player.getBettingMoney()
            }.run {
                BettingResult(this)
            }
        }

        private const val TIMES = 1.5
        private const val NOT_PROFIT = 0
    }
}
