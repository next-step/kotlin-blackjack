package blackjack.ui

import blackjack.domain.Player

object BlackjackResultView {
    fun resultOfInitialDraw(players: List<Player>) {
        println(
            "Two cards was drawn by " +
                players.joinToString(", ") { it.name }
        )
        players.forEach { println(it.toString()) }
    }

    fun resultOfDraw(player: Player) {
        println(player)
    }

    fun resultOfGame(players: List<Player>) {
        players.forEach { player ->
            println("$player - score: ${player.result()}")
        }
    }
}
