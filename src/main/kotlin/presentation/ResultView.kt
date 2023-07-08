package presentation

import domain.player.Dealer
import domain.player.Player

object ResultView {
    fun printInitialState(dealer: Dealer, players: List<Player>) {
        println("${dealer.name}와 ${players.names().joinToString(", ")}에게 2장의 카드를 나누었습니다.")
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
        println("$name 카드: ${cards.current().first()}")
    }

    private fun Player.printWithResult() {
        println("$name 카드: ${cards.current().joinToString(", ")} - 결과: ${result()}")
    }
}
