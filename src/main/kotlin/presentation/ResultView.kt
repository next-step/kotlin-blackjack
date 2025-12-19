package presentation

import domain.Dealer
import domain.Player

class ResultView {
    companion object {
        fun printParticipantCardResult(
            players: List<Player>,
            dealer: Dealer,
        ) {
            println("딜러 카드: ${dealer.displayCardInfo()} - 결과: ${dealer.score()}")
            players.forEach { player ->
                println("${player.name} 카드: ${player.displayCardInfo()} - 결과: ${player.score()}")
            }
        }

        fun printDistributedCardInfos(players: List<Player>, dealer: Dealer) {
            println("딜러와 ${players.joinToString(", ") { it.name }}에게 2장의 나누었습니다.")

            // 딜러 카드 출력 : 한장만 출력
            println("딜러: ${dealer.openFirstRound()}")

            // 플레이어 카드 출력 : 플레이어별 전체 카드 출력
            players.forEach {
                println("${it.name}카드: ${it.openFirstRound()}")
            }
        }

        fun printParticipantProfit(players: List<Player>, dealer: Dealer) {
            println("## 최종 수익")
            println("딜러: ${dealer.profit}")
            players.forEach { player ->
                println("${player.name}: ${player.profit}")
            }
        }
    }
}
