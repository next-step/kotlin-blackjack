package blackjack.domain.score

import blackjack.domain.player.Player
import blackjack.domain.player.Players

object ScoreManager {
    fun isBustPlayer(player: Player): Boolean {
        return player.getScore().isBust()
    }

    fun isNotBustPlayer(player: Player): Boolean {
        return !player.getScore().isBust()
    }

    fun isBlackJack(player: Player): Boolean {
        return player.getScore().isBlackJack()
    }

    fun isNotBlackJack(player: Player): Boolean {
        return !player.getScore().isBlackJack()
    }

    fun getGameResults(players: Players): List<GameResult> {
        return players.players.map { GameResult(it, it.getScore()) }
    }
}
