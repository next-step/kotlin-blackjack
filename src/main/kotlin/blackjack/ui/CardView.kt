package blackjack.ui

import blackjack.domain.Player
import blackjack.ui.model.BlackJackResult

object CardView {

    fun printCardsOf(players: List<Player>) {
        val names = players.joinToString(", ") { it.name }
        println("${names}에게 2장의 나누었습니다.")
        for (player in players) {
            printCardsOf(player)
        }
        println()
    }

    fun printCardsOf(player: Player) {
        println("${player.name}: ${player.cardNames.joinToString()}")
    }

    fun printResults(blackJackResults: List<BlackJackResult>) {
        println()
        for (blackJackResult in blackJackResults) {
            printSingleResult(blackJackResult)
        }
    }

    private fun printSingleResult(blackJackResult: BlackJackResult) {
        println()
        println("${blackJackResult.name}: ${blackJackResult.cardNames.joinToString()} - 결과: ${blackJackResult.point}")
    }
}
