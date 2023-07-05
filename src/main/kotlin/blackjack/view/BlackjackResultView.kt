package blackjack.view

import blackjack.domain.Cards
import blackjack.domain.Dealer
import blackjack.domain.Players
import blackjack.vo.GameProfitResult
import blackjack.vo.GameResult

object BlackjackResultView {
    fun printParticipantsResult(dealer: Dealer, players: Players) {
        println()
        printParticipantResult(dealer.name, dealer.getCards(), dealer.sumOfCards())
        players.forEach {
            printParticipantResult(it.name, it.getCards(), it.sumOfCards())
        }
    }

    private fun printParticipantResult(name: String, cards: Cards, sumOfCards: Int) {
        println("$name: ${BlackjackView.printCards(cards)} - 결과: $sumOfCards")
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
