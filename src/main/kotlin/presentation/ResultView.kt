package presentation

import domain.Result
import domain.gamer.Gamer
import domain.gamer.dealer.Dealer
import domain.gamer.player.Player

object ResultView {
    fun printInitialState(dealer: Dealer, players: List<Player>) {
        println("딜러와 ${players.names().joinToString(", ")}에게 2장의 카드를 나누었습니다.")
        dealer.print()
        players.print()
        println()
    }

    fun printResult(dealer: Dealer, players: List<Player>) {
        dealer.printWithResult()
        players.forEach {
            it.printWithResult()
        }
        println()
    }

    fun printDealerReceiveCardMessage() {
        println("딜러는 ${Dealer.DEALER_MAX_POINT}이하라 한장의 카드를 더 받았습니다.")
        println()
    }

    fun printResult(result: Result) {
        println("## 최종 승패")
        println("딜러: ${result.numOfLoser}승 ${result.numOfWinner}패")
        result.winners.names().forEach {
            println("$it: 승")
        }
        result.losers.names().forEach {
            println("$it: 패")
        }
    }

    private fun List<Player>.names(): List<String> {
        return map { it.name }
    }

    private fun List<Player>.print() {
        forEach {
            it.print()
        }
        println()
    }

    private fun Player.print() {
        println("$name 카드: ${cards.current().joinToString(", ")}")
    }

    private fun Dealer.print() {
        println("딜러 카드: ${cards.current().first()}")
    }

    private fun Gamer.printWithResult() {
        println("$name 카드: ${cards.current().joinToString(", ")} - 결과: $score")
    }
}
