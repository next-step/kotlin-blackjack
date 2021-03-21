package blackjack.ui

import blackjack.ui.model.BlackJackResult
import blackjack.ui.model.PlayerDto

object CardView {

    fun printCardsOf(playerDtos: List<PlayerDto>) {
        val names = playerDtos.joinToString(", ") { it.name }
        println("${names}에게 2장의 나누었습니다.")
        for (playerDTO in playerDtos) {
            printCardsOfSinglePlayer(playerDTO)
        }
        println()
    }

    fun printCardsOfSinglePlayer(playerDto: PlayerDto) {
        println("${playerDto.name}: ${playerDto.cards.joinToString(", ") { it.toString() }}")
    }

    fun printResults(blackJackResults: List<BlackJackResult>) {
        println()
        for (blackJackResult in blackJackResults) {
            printSingleResult(blackJackResult)
        }
    }

    private fun printSingleResult(blackJackResult: BlackJackResult) {
        println("${blackJackResult.name}: ${blackJackResult.cardNames.joinToString()} - 결과: ${blackJackResult.point}")
    }
}
