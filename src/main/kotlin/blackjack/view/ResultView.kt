package blackjack.view

import blackjack.domain.Player

class ResultView {

    fun players(players: List<String>) {
        println(players)
        println("${players.joinToString(", ")} 에게 2장의 나누었습니다.")
    }

    fun firstRoundState(players: List<Player>) {
        println()
        for (player in players) {
            println("${player.name}카드: ${player.cards.joinToString(", ")}")
        }
    }
}
