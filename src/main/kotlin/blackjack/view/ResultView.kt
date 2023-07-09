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
        println("${player.name}카드: ${playerCards(player)}")
    }

    fun printResult(players: Players) {
        println()
        players.forEach { player ->
            println("${player.name}카드: ${playerCards(player)} - 결과: ${player.score()}")
        }
    }

    private fun playerCards(player: Player) =
        player.cards.cards.joinToString(", ") { it -> "${it.cardType.label}${it.denomination.label}" }
}
