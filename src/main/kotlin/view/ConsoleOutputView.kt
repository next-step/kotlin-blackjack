package view

import domain.Dealer
import domain.Player
import enum.GameResult

class ConsoleOutputView : OutputView {
    override fun showGameState(players: List<Player>, dealer: Dealer) {
        players.forEach { player ->
            println("${player.name}의 카드: ${player.showHand().joinToString(", ")}")
        }
        println("딜러의 카드: ${dealer.showHand().joinToString(", ")}")
    }

    override fun showPlayerResults(players: List<Player>, dealer: Dealer) {
        println("게임 결과:")
        players.forEach { player ->
            println("${player.name}: ${player.showHand().joinToString(", ")} - 결과: ${player.calculateScore()}")
        }
        println("딜러: ${dealer.showHand().joinToString(", ")} - 결과: ${dealer.calculateScore()}")
    }

    override fun showFinalResults(players: List<Player>, dealer: Dealer) {
        println("## 최종 승패")
        println("딜러: ${calculateDealerResult(players)}")
        players.forEach { player ->
            println("${player.name}: ${convertResultToString(player.result)}")
        }
    }

    override fun showInitialCards(players: List<Player>, dealer: Dealer) {
        val playerNames = players.joinToString(", ") { it.name }
        println("$playerNames 에게 2장의 카드를 나누었습니다.")
        players.forEach { player ->
            println("${player.name}의 카드: ${player.showHand().joinToString(", ")}")
        }
        println("딜러의 카드: ${dealer.showHand().joinToString(", ")}")
    }

    private fun convertResultToString(result: GameResult): String {
        return when (result) {
            GameResult.WIN, GameResult.BLACKJACK_WIN -> "승"
            GameResult.LOSE -> "패"
            GameResult.DRAW -> "무승부"
        }
    }

    private fun calculateDealerResult(players: List<Player>): String {
        val wins = players.count { it.result == GameResult.LOSE }
        val losses = players.count { it.result == GameResult.WIN }
        return "${wins}승 ${losses}패"
    }
}
