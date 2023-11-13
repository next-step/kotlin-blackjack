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
            .forEach {
                print("${it.name}카드: ")
                printCard(it)
            }
    }

    private fun printCard(player: Player) {
        val playerCards = player.hand
            .cards
            .joinToString { "${it.num.symbol}${it.suit.value}" }
        println(playerCards)
    }
}
