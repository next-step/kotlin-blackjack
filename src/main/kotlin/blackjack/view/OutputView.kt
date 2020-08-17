package blackjack.view

import blackjack.model.player.Players

object OutputView {
    fun firstTurn(players: Players) {
        val name = players.getNames()
        println("${name}에게 2장의 카드를 나누었습니다.")
    }

    fun printDealerCall() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printCardForPlayers(players: Players) {
        printPlayers(players.getDrawCardResults())
    }

    fun printPointResult(players: Players) {
        printPlayers(players.getPointResults())
    }

    fun printPrize(players: Players) {
        println("## 최종 수익")
        printPlayers(players.getPrize())
    }

    private fun printPlayers(results: List<String>) {
        for (result in results) {
            println(result)
        }
    }
}
