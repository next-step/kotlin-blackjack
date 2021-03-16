package blackjack.view

import blackjack.domain.Game
import blackjack.domain.Player

object ResultView {

    fun printStart(players: Set<Player>) {
        println()

        val playerNames = players.joinToString(", ") { it.name }
        println("${playerNames}에게 ${Game.FIRST_DRAW_COUNT}장을 나누어 주었습니다.")

        players.forEach {
            printPlayerCards(it)
        }

        println()
    }

    fun printPlayerCards(player: Player) {
        val cardNames = player.cards.joinToString(", ") { it.toString() }
        println("${player.name}카드: $cardNames")
    }

    fun printResult(players: Set<Player>) {
        println()

        players.forEach {
            printPlayerResult(it)
        }
    }

    private fun printPlayerResult(player: Player) {

        val cardNames = player.cards.joinToString(", ") { it.toString() }
        println("${player.name}카드: $cardNames - 결과: ${player.score}")
    }
}
