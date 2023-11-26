package blackjack.view

import blackjack.domain.Player
import blackjack.domain.Players

object ResultView {
    fun printPlayerStates(players: Players, size: Int) {
        val playerNames = players.getNames().joinToString(",")
        println("\n${playerNames}에게 ${size}장의 카드를 나누었습니다.")
        players.players.forEach {
            printPlayerCards(it)
        }
        println()
    }

    fun printPlayerCards(player: Player) {
        println("${player.name}카드: ${player.cards}")
    }

    fun printResult(player: Player, score: Int) {
        print("\n${player.name}카드: ${player.cards} - 결과: $score")
    }
}
