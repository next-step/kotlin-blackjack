package blackjack.ui

import blackjack.Player

class OutputManager {

    fun printFirstTurn(players: List<Player>) {
        val names: String = players.map { it.name }.joinToString(", ")

        println("${names}에게 두장의 카드를 나누었습니다.")
    }
}
