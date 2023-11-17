package view

import domain.Dealer
import domain.Player

class ConsoleOutputView : OutputView {
    override fun showGameState(players: List<Player>, dealer: Dealer) {
        players.forEach { player ->
            println("${player.name}의 카드: ${player.showHand().joinToString(", ")}")
        }
        println("딜러의 카드: ${dealer.showHand().joinToString(", ")}")
    }

    override fun showGameResult(players: List<Player>, dealer: Dealer) {
        println("게임 결과:")
        players.forEach { player ->
            println("${player.name}: ${player.showHand().joinToString(", ")} - 결과: ${player.calculateScore()}")
        }
        println("딜러: ${dealer.showHand().joinToString(", ")} - 결과: ${dealer.calculateScore()}")
    }

    override fun showInitialCards(players: List<Player>, dealer: Dealer) {
        val playerNames = players.joinToString(", ") { it.name }
        println("$playerNames 에게 2장의 카드를 나누었습니다.")
        players.forEach { player ->
            println("${player.name}의 카드: ${player.showHand().joinToString(", ")}")
        }
        println("딜러의 카드: ${dealer.showHand().joinToString(", ")}")
    }
}
