package blackJack.domain.result

import blackJack.domain.player.Dealer
import blackJack.domain.player.Players

class PlayersResult(val playersResult: List<PlayerResult>) {
    companion object {
        fun calculateResult(players: Players, dealer: Dealer): PlayersResult {
            val playersResult = players.players.map { PlayerResult.calculateResult(it, dealer) }
            return PlayersResult(playersResult)
        }
    }
}
