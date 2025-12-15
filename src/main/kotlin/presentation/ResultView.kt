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

        fun printDistributedCardInfos(players: Set<Player>, dealer: Dealer) {
            println("딜러와 ${players.joinToString(", ") { it.name }}에게 2장의 나누었습니다.")

            // 딜러 카드 출력 : 한장만 출력
            println("딜러: ${dealer.getOneVisibleCardInfo()}")

            // 플레이어 카드 출력 : 플레이어별 전체 카드 출력
            players.forEach {
                println("${it.name}카드: ${it.blackjackCards.displayCardInfo()}")
            }
        }
    }
}
