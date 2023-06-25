package blackjack.domain.score

import blackjack.domain.player.Players

object GameResultManager {
    fun getGameResults(players: Players): List<GameResult> {
        return players.players.map { GameResult(it, it.score) }
    }
}
