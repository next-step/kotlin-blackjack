package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.forEachPlayer
import blackjack.vo.BettingResultVO
import blackjack.vo.GameResultVO

object BlackjackResultView {
    fun printParticipantsResult(dealer: Dealer, players: Players) {
        println()
        printDealerResult(dealer)
        players.forEachPlayer {
            printPlayerResult(it)
        }
    }

    private fun printDealerResult(dealer: Dealer) {
        println("${dealer.name}: ${BlackjackView.printCards(dealer.getMyCards())} - 결과: ${dealer.sumOfMyCards()}")
    }

    private fun printPlayerResult(player: Player) {
        println("${player.name}카드: ${BlackjackView.printCards(player.getMyCards())} - 결과: ${player.sumOfMyCards()}")
    }

    fun printGameResult(gameResult: GameResultVO) {
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

    fun printBettingResult(bettingResult: BettingResultVO) {
        println("\n## 최종 수익")

        println("딜러: ${bettingResult.dealerProfit}")
        bettingResult.playersProfits.forEach { (player, profit) ->
            println("${player.name}: $profit")
        }
    }
}
