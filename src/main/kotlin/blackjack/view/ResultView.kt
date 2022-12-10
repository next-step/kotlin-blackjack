package blackjack.view

import blackjack.domain.Game
import blackjack.domain.Player

object ResultView {
    fun printInitialStatus() {
        printPlayersName(Game.players)
        printPlayersCardStatus(Game.players)
    }

    private fun printPlayersName(players: List<Player>) {
        val names = players.joinToString(", ") { it.name }

        println("\n$names 에게 2장의 카드를 나누었습니다.")
    }

    private fun printPlayersCardStatus(players: List<Player>) {
        players.forEach { player ->
            val cards = player.cards.values
                .joinToString(", ") { it.toString() }
            println("${player.name} 카드: $cards")
        }
    }
}
