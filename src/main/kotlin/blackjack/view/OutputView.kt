package blackjack.view

import blackjack.model.player.Players

object OutputView {
    fun firstTurn(players: Players) {
        val name = players.getNames()
        println("${name}에게 2장의 카드를 나누었습니다.")
    }

    fun printCardForPlayers(players: Players) {
        printPlayers(players.getDrawCardResults())
    }

    fun printPoint(players: Players) {
        printPlayers(players.getPointResults())
    }

    fun printWinOrLost(players: Players) {
        printPlayers(players.getWinnerResults())
    }

    private fun printPlayers(results: List<String>) {
        for (result in results) {
            println(result)
        }
    }
}
