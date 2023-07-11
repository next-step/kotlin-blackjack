package blackjack.domain.gameresult

import blackjack.domain.player.Player

class GameResultManager(private val referee: Referee) {
    fun calculate(players: List<Player>): GameResultSummary {

        val playerResults: List<PlayerResult> = players.map { player ->
            val result = referee.referee(player)
            PlayerResult(player, result.profit(player.money))
        }

        return GameResultSummary(playerResults)
    }
}
