package blackjack.view.output

import blackjack.domain.DealerResult
import blackjack.domain.PlayerResult
import blackjack.domain.player.GamePlayers
import blackjack.domain.player.GameResult

class GamePlayersResultOutputView(gamePlayers: GamePlayers) {
    init {
        val gameResult = gamePlayers.getGameResult()
        println("\n### 최종 승패")
        renderDealerMessage(gameResult.dealer)
        gameResult.players.forEach { renderPlayerMessage(it) }
    }

    private fun renderDealerMessage(result: DealerResult) {
        val resultMessage = listOf(GameResult.WIN, GameResult.LOOSE).joinToString(" ") { gameResult ->
            result.getMatchedGameResultCount(gameResult).toString() + gameResult.korName
        }
        println("딜러: $resultMessage")
    }

    private fun renderPlayerMessage(player: PlayerResult) {
        println("${player.player.name.value}: ${player.result.korName}")
    }
}
