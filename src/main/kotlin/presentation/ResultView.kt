package presentation

import domain.Dealer
import domain.GameOutcome
import domain.Player

class ResultView {
    companion object {
        fun printWinnerResult(result: Map<Player, GameOutcome>) {
            println("## 최종승패")

            // 딜러 승패 출력
            val dealerDefeatCount = result.count { GameOutcome.isWin(it.value) }
            val dealerWinCount = result.count { !GameOutcome.isWin(it.value) }
            println("딜러: ${dealerWinCount}승 ${dealerDefeatCount}패")

            // 플레이어 승패 출력
            result.forEach { (player, isWin) ->
                val resultText = if (GameOutcome.isWin(isWin)) "승" else "패"
                println("${player.name}: $resultText")
            }
        }

        fun printParticipantCardResult(
            players: Set<Player>,
            dealer: Dealer,
        ) {
            println("딜러 카드: ${dealer.displayCardInfo()} - 결과: ${dealer.calculateScore()}")
            players.forEach { player ->
                println("${player.name} 카드: ${player.displayCardInfo()} - 결과: ${player.calculateScore()}")
            }
        }

        fun printDistributedCardInfos(players: Set<Player>, dealer: Dealer) {
            println("딜러와 ${players.joinToString(", ") { it.name }}에게 2장의 나누었습니다.")

            // 딜러 카드 출력 : 한장만 출력
            println("딜러: ${dealer.openFirstRound()}")

            // 플레이어 카드 출력 : 플레이어별 전체 카드 출력
            players.forEach {
                println("${it.name}카드: ${it.openFirstRound()}")
            }
        }

        fun printParticipantProfit(players: Set<Player>, dealer: Dealer) {
            println("## 최종 수익")
            println("딜러: ${dealer.profit}")
            players.forEach { player ->
                println("${player.name}: ${player.profit}")
            }
        }
    }
}
