package blackJack.domain.result

import blackJack.domain.enums.BlackjackResult.*
import blackJack.domain.player.Dealer
import blackJack.domain.player.Players

class PlayersResult(val playersResult: List<PlayerResult>) {

    fun countPlayerWin(): Int = playersResult.count { it.result == LOSE }
    fun countPlayerDraw(): Int = playersResult.count { it.result == DRAW }
    fun countPlayerLose(): Int = playersResult.count { it.result == WIN }
    fun countPlayerBlackJackWin(): Int = playersResult.count { it.result == BLACKJACK }

    companion object {
        fun calculateResult(players: Players, dealer: Dealer): PlayersResult {
            val playersResult = players.players.map { PlayerResult.calculateResult(it, dealer) }
            return PlayersResult(playersResult)
        }
    }
}
