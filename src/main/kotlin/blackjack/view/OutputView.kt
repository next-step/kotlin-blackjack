package blackjack.view

import blackjack.model.Player
import blackjack.model.Players

class OutputView {

    fun printFirstDraw(players: Players) {
        val names = players.map { it.name }
        val count = players.map { it.cards.size }.minOrNull() ?: 0
        if (count == 0) return
        println()
        println("${names.joinToString()}에게 ${count}장의 카드를 나누었습니다.")
        players.forEach { player -> printPlayerCards(player) }
        println()
    }

    fun printPlayerCards(player: Player, newline: Boolean = true) {
        print("${player.name}카드: ${player.cards.joinToString()}")
        if (newline) println()
    }

    fun printResult(players: Players) {
        players.forEach { player ->
            printPlayerCards(player, false)
            println(" - 결과: ${player.cards.sum()}")
        }
    }
}
