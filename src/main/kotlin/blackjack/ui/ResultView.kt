package blackjack.ui

import blackjack.domain.Player
import blackjack.domain.Players

object ResultView {

    fun printInitPlayers(players: Players) {
        val playerNames = players.getNames()
            .joinToString { it }
        println()
        println("${playerNames}에게 2장의 카드를 나누었습니다.")

        players.players
            .forEach { printPlayerNameAndCard(it) }
        println()
    }

    fun printPlayerNameAndCard(player: Player) {
        printName(player)
        printCard(player)
        println()
    }

    fun printResult(players: Players) {
        println()
        for (player in players.players) {
            printName(player)
            printCard(player)
            printSum(player)
            println()
        }
    }

    private fun printName(player: Player) {
        print("${player.name}카드: ")
    }

    private fun printCard(player: Player) {
        val playerCards = player.hand
            .cards
            .joinToString { "${it.num.symbol}${it.suit.value}" }
        print(playerCards)
    }

    private fun printSum(player: Player) {
        print(" - 결과: ${player.hand.getSum()}")
    }
}
