package presentation

import domain.Dealer
import domain.Player

class ResultView {
    companion object {
        fun printWinnerResult(result: Map<Player, Boolean>) {
            println("## 최종승패")

            // 딜러 승패 출력
            val dealerDefeatCount = result.count { it.value }
            val dealerWinCount = result.count { !it.value }
            println("딜러: ${dealerWinCount}승 ${dealerDefeatCount}패")

            // 플레이어 승패 출력
            result.forEach { (player, isWin) ->
                val resultText = if (isWin) "승" else "패"
                println("${player.name}: $resultText")
            }
        }

        fun printParticipantCardResult(
            players: Set<Player>,
            dealer: Dealer,
        ) {
            println("딜러 카드: ${dealer.blackjackCards.displayCardInfo()} - 결과: ${dealer.blackjackCards.calculateScore()}")
            players.forEach { player ->
                println("${player.name} 카드: ${player.blackjackCards.displayCardInfo()} - 결과: ${player.blackjackCards.calculateScore()}")
            }
        }
    }
}
