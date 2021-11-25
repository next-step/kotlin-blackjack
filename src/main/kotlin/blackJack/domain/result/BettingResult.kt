package blackJack.domain.result

import blackJack.domain.player.Dealer
import blackJack.domain.player.Player

class BettingResult(val profit: Int) {

    companion object {
        fun of(player: Player, dealer: Dealer, winDrawLose: WinDrawLose): BettingResult {
            if (player.isBlackJackPlayer() && !dealer.isBlackJackPlayer()) {
                return BettingResult((player.getBettingMoney() * 1.5).toInt())
            }
            return when (winDrawLose) {
                WinDrawLose.WIN -> BettingResult(player.getBettingMoney())
                WinDrawLose.DRAW -> BettingResult(0)
                WinDrawLose.LOSE -> BettingResult(-player.getBettingMoney())
            }
        }
    }
}
