package blackjack.view

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.state.GameResult

object OutputView {
    fun printInitialCards(dealer: Dealer, players: List<Player>) {
        val playerNames = players.joinToString(", ") { it.toString() }
        println("\n딜러와 $playerNames 에게 2장의 카드를 나누었습니다.")
        printDealerInitialCard(dealer)
        players.forEach { player ->
            printPlayerCards(player)
        }
        println()
    }

    fun printPlayerCards(player: Player) {
        println("${player}카드: ${player.cards().joinToString(", ")}")
    }

    fun printDealerInitialCard(dealer: Dealer) {
        println("딜러: ${dealer.cards()}")
    }

    fun printDealerDrawMessage() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n")
    }

    fun printFinalCards(dealer: Dealer, players: List<Player>) {
        println("딜러 카드: ${dealer.cards().joinToString(", ")} - 결과: ${dealer.score()}")
        players.forEach { player ->
            println("${player}카드: ${player.cards().joinToString(", ")} - 결과: ${player.score()}")
        }
        println()
    }

    fun printFinalResults(dealerResult: Map<GameResult, Int>, playerResults: Map<Player, GameResult>) {
        println("## 최종 승패")
        val dealerWins = dealerResult[GameResult.WIN] ?: 0
        val dealerLoses = dealerResult[GameResult.LOSE] ?: 0
        println("딜러: ${dealerWins}승 ${dealerLoses}패")

        playerResults.forEach { (player, result) ->
            println("${player}: ${result.toString().lowercase()}")
        }
    }
}
