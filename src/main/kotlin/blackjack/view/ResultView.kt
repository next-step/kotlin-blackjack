package blackjack.view

import blackjack.player.Player
import blackjack.player.Players

object ResultView {
    fun printPlayersName(players: Players) {
        println("${players.players.joinToString { it.name }}에게 ${players.players.size}장을 나누었습니다.")
    }

    fun printPlayersCardStatus(players: Players) {
        players.players.forEach { player ->
            printPlayerCard(player)
        }
        println()
    }

    fun printPlayerCard(player: Player) {
        println("${player.name}카드: ${player.cards.joinToString(", ") { "${it.rank.value}${it.suit.description}" }}")
    }
}
