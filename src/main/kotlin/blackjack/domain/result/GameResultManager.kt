package blackjack.domain.result

import blackjack.domain.player.GamePlayers

object GameResultManager {
    fun getGameResults(players: GamePlayers): List<GameResult> {
        return players.players.map { GameResult(it, it.score) }
    }
}
