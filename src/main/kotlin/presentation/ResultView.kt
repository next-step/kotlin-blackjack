package presentation

import domain.participant.Dealer
import domain.participant.Player
import domain.participant.WinType
import kotlin.math.ceil

class ResultView {
    companion object {
        fun printWinnerResult(result: Map<Player, WinType>) {
            println("## 최종승패")

            // 딜러 승패 출력
            val dealerDefeatCount = result.count { it.value == WinType.WIN }
            val dealerWinCount = result.count { it.value == WinType.LOSE }
            val drawCount = result.count { it.value == WinType.DRAW }
            println("딜러: ${dealerWinCount}승 ${dealerDefeatCount}패 ${drawCount}무")

            // 플레이어 승패 출력
            result.forEach { (player, winType) ->
                println("${player.name}: ${winType.displayName}")
            }
        }

        fun printProfit(result: Map<Player, WinType>) {
            println("## 최종 수익")
            val playerProfits =
                result.mapValues { (player, winType) ->
                    when (winType) {
                        WinType.WIN ->
                            ceil(player.betMoney.amount * if (player.isBlackjack()) 1.5 else 1.0).toInt()

                        WinType.LOSE -> -player.betMoney.amount
                        WinType.DRAW -> 0
                    }
                }

            println("딜러: ${-playerProfits.values.sum()}")
            playerProfits.forEach { (player, profit) ->
                println("${player.name}: ${profit}원")
            }
        }

        fun printParticipantCardResult(
            players: List<Player>,
            dealer: Dealer,
        ) {
            println("딜러 카드: ${dealer.hand} - 결과: ${dealer.hand.calculateScore()}")
            players.forEach { player ->
                println("${player.name} 카드: ${player.hand} - 결과: ${player.hand.calculateScore()}")
            }
        }

        fun printDistributedCardInfos(
            players: List<Player>,
            dealer: Dealer,
        ) {
            println("딜러와 ${players.joinToString(", ") { it.name }}에게 2장의 나누었습니다.")

            // 딜러 카드 출력 : 한장만 출력
            println("딜러: ${dealer.getOneVisibleCardInfo()}")

            // 플레이어 카드 출력 : 플레이어별 전체 카드 출력
            players.forEach {
                println("${it.name}카드: ${it.hand}")
            }
        }
    }
}
