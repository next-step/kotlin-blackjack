package blackjack.view

import blackjack.model.Player
import blackjack.model.Players

class OutputView {

    fun printFirstDraw(players: Players, count: Int) {
        println()
        val names = players.map { it.name }
        println("${names.joinToString()}에게 ${count}장의 카드를 나누었습니다.")
        players.forEach { player -> printPlayerCards(player) }
        println()
    }

    fun printPlayerCards(player: Player) {
        println("${player.name}카드: ${player.cards.joinToString()}")
    }

    fun printResult(players: Players) {
        println()
        players.forEach { player ->
            println("${player.name}카드: ${player.cards.joinToString()} - 결과: ${player.cards.sum()}")
        }
        println()
    }
}
