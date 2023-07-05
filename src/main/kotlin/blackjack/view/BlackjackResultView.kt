package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.vo.GameProfitResult
import blackjack.vo.GameResult

object BlackjackResultView {
    fun printParticipantsResult(dealer: Dealer, players: Players) {
        println()
        printDealerResult(dealer)
        players.forEach {
            printPlayerResult(it)
        }
    }

    private fun printDealerResult(dealer: Dealer) {
        println("${dealer.name}: ${BlackjackView.printCards(dealer.getCards())} - 결과: ${dealer.sumOfCards()}")
    }

    private fun printPlayerResult(player: Player) {
        println("${player.name}카드: ${BlackjackView.printCards(player.getCards())} - 결과: ${player.sumOfCards()}")
    }

    fun printGameResult(gameResult: GameResult) {
        println("\n## 최종 승패")

        val dealerResults = gameResult.dealerWinMap.map { (result, int) ->
            int.toString() + result.description
        }

        println(
            "딜러: ${dealerResults.joinToString(" ")}",
        )

        gameResult.playersWinMap.forEach { (player, result) ->
            println("${player.name}: ${result.description}")
        }
    }

    fun printBettingResult(bettingResult: GameProfitResult) {
        println("\n## 최종 수익")

        println("딜러: ${bettingResult.dealerProfit}")
        bettingResult.playersProfits.forEach { (player, profit) ->
            println("${player.name}: $profit")
        }
    }
}
