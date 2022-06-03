package blackjack.ui

import blackjack.domain.Player
import blackjack.domain.Players

object UI {

    fun drawFirstTurnMessage(players: Players) {
        val players = players.list.joinToString(",") { it.name }
        println("${players}에게 각자 2장을 나누었습니다.")
    }

    fun drawCardList(player: Player) {
        println("${player.name}카드: ${player.currentCards().joinToString(", ") { it.toString() }}")
    }
}
