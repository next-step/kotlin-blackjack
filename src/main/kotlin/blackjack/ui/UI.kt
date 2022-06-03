package blackjack.ui

import blackjack.domain.Player
import blackjack.domain.Players

object UI {

    fun drawCardDistributeMessage(players: Players, count: Int) {
        val players = players.list.joinToString(",") { it.name }
        println("${players}에게 각자 ${count}장을 나누었습니다.")
    }

    fun drawCardList(player: Player) {
        println("${player.name}카드: ${player.cards.joinToString(", ") { it.toString() }}")
    }
}
