package blackjack.view

import blackjack.model.Player
import blackjack.model.Players
import blackjack.view.res.getString

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
        val name = player.name
        val cardsDisplay = player.cards.toList().joinToString {
            "${it.denomination.symbol}${getString(it.suit)}"
        }
        print("${name}카드: $cardsDisplay")
        if (newline) println()
    }

    fun printResult(players: Players) {
        players.forEach { player ->
            printPlayerCards(player, false)
            println(" - 결과: ${player.cards.sum()}")
        }
    }
}
