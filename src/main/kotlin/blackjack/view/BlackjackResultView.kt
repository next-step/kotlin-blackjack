package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Participant
import blackjack.domain.Players
import blackjack.vo.GameProfitResult
import blackjack.vo.GameResult

object BlackjackResultView {
    fun printParticipantsResult(dealer: Dealer, players: Players) {
        println()
        printParticipantResult(dealer)
        players.forEach {
            printParticipantResult(it)
        }
    }

    private fun printParticipantResult(participant: Participant) {
        val cards = BlackjackView.printCards(participant.getCards())
        println("$participant.name: $cards - 결과: $participant.sumOfCards")
    }

    fun printGameResult(gameResult: GameResult) {
        println("\n## 최종 승패")

        val dealerResults = gameResult.dealerWinMap.map { (result, int) ->
            int.toString() + result.description
        }

        println("딜러: ${dealerResults.joinToString(" ")}")
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
