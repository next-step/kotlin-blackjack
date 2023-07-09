package blackjack.view

import blackjack.domain.Player
import blackjack.domain.Players

object ResultView {
    fun printPlayers(players: Players) {
        val playersName = players.list.joinToString(", ") { it.name }

        println("${playersName}에게 2장의 카드를 나누어주었습니다.")

        players.list.forEach { player ->
            printPlayer(player)
        }
    }

    fun printPlayer(player: Player) {
        print("${player.name}카드: ")
        println(player.cards.cards.joinToString(", ") { it -> "${it.cardType.label}${it.denomination.label}" })
    }
}
