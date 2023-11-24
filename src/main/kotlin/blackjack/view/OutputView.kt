package blackjack.view

import blackjack.domain.Player
import blackjack.domain.Players

object OutputView {
    fun printPlayerStates(players: Players, initialDealSize: Int) {
        val playerNames = players.getNames().joinToString(", ")
        println("\n${playerNames}에게 ${initialDealSize}장의 나누었습니다.po")
        players.forEach {
            printPlayerState(it)
        }
        println()
    }

    fun printPlayerState(player: Player) {
        println("${player.name}카드: ${player.cards}")
    }

    fun printGameScore(player: Player, score: Int) {
        print("\n${player.name}카드: ${player.cards} - 결과: $score")
    }
}
