package blackjack.view

import blackjack.domain.Dealer.Companion.DEALER_NAME
import blackjack.domain.GamePlayers
import blackjack.domain.GameResult
import blackjack.domain.Player
import blackjack.domain.Result

object OutputView {
    fun firstCard(gamePlayers: GamePlayers) {
        val allPlayers = gamePlayers.allPlayers
        val result = buildString {
            appendLine("\n${allPlayers.joinToString(", ") { it.name }} 에게 2장의 카드를 나누어 주었습니다.")
            allPlayers.forEach { player ->
                appendLine(playerCardToString(player))
            }
        }
        print(result)
    }

    fun cardOfPlayer(player: Player) {
        println(playerCardToString(player))
    }

    fun gameScoreResult(gamePlayers: GamePlayers) {
        val result = buildString {
            appendLine("==========================")
            gamePlayers.allPlayers.forEach { player ->
                append(playerCardToString(player))
                appendLine(" - 결과: ${player.sumOfPoints()}")
            }
        }
        println(result)
    }

    fun gameWinResult(gameResults: List<GameResult>) {
        val dealerWinCount = gameResults.count { it.result == Result.LOOSE }
        val dealerLooseCount = gameResults.count { it.result == Result.WIN }
        val dealerDrawCount = gameResults.count { it.result == Result.DRAW }

        println("## 최종 승패")
        println("$DEALER_NAME: ${dealerWinCount}승 ${dealerDrawCount}무 ${dealerLooseCount}패")
        println(playerWinResult(gameResults))
    }

    private fun playerWinResult(gameResults: List<GameResult>) = buildString {
        gameResults.forEach {
            appendLine("${it.player.name}: ${it.result.display}")
        }
    }

    private fun playerCardToString(player: Player) = buildString {
        append(
            "${player.name}카드: ${
            player.hands.cards.joinToString(", ") { it.number.displayStr + it.symbol.displayStr }
            }"
        )
    }
}
