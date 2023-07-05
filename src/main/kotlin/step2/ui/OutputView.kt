package step2.ui

import step2.domain.Player
import step2.domain.Players

object OutputView {

    fun printDrawInitialCards(players: Players) {
        val names = players.players.joinToString { it.name }
        println("${names}에게 ${players.players.size}장의 나누었습니다.")
        players.players.forEach {
            printPlayersCards(it)
        }
        println()
    }

    fun printPlayersCards(player: Player) {
        println("${player.name}카드: ${player.cards.cards.map { it.display() }.joinToString(",")}")
    }
}
