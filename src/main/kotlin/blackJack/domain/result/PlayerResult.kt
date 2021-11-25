package blackJack.domain.result

import blackJack.domain.player.Dealer
import blackJack.domain.player.Player

class PlayerResult(
    val name: String,
    val winDrawLose: WinDrawLose,
    private val bettingResult: BettingResult
) {

    fun getProfit(): Int = bettingResult.profit

    companion object {
        fun winOrLose(player: Player, dealer: Dealer): PlayerResult {
            val playerWinDrawLose = WinDrawLose.from(player, dealer)
            val bettingResult = BettingResult.of(player, dealer, playerWinDrawLose)
            return PlayerResult(player.name, playerWinDrawLose, bettingResult)
        }
    }
}
