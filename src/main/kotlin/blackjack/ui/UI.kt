package blackjack.ui

import blackjack.domain.Player
import blackjack.domain.Players

object UI {

    fun drawFirstTurnMessage(players: Players) {
        val playerNames = players.list.joinToString(",") { it.name }
        println("딜러와 ${playerNames}에게 각자 2장을 나누었습니다.")
    }

    fun drawCardList(player: Player) {
        println("${player.name}카드: ${player.currentCards().joinToString(", ") { it.toString() }}")
    }

    fun drawResult(player: Player) {
        println("${player.name}카드: ${player.currentCards().joinToString(", ") { it.toString() }} - ${player.score}")
    }

    fun drawDivider() {
        println()
    }
}
