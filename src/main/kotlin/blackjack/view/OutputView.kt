package blackjack.view

import blackjack.domain.game.START_DRAW_COUNT
import blackjack.domain.player.Player
import blackjack.domain.player.Players

object OutputView {
    fun printPlayerHandsView(player: Player) {
        println("${player.name}카드: ${player.getHands().joinToString(", ")}")
    }

    fun printPlayersHandsView(players: Players) {
        players.players.forEach {
            printPlayerHandsView(it)
        }
        println()
    }

    fun printPlayersInitView(players: Players) {
        println("${players.players.joinToString(", ") { it.name }}에게 ${START_DRAW_COUNT}장을 나누었습니다.")
    }

    fun printPlayersResultView(players: Players) {
        players.players.forEach {
            println("${it.name}카드: ${it.getHands().joinToString(", ")} - 결과: ${it.getHandsValue()}")
        }
    }
}
