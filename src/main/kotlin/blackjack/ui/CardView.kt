package blackjack.ui

import blackjack.ui.model.BlackJackResult
import blackjack.ui.model.PlayerDTO

object CardView {

    fun printCardsOf(playerDTOs: List<PlayerDTO>) {
        val names = playerDTOs.joinToString(", ") { it.name }
        println("${names}에게 2장의 나누었습니다.")
        for (playerDTO in playerDTOs) {
            printCardsOfSinglePlayer(playerDTO)
        }
        println()
    }

    fun printCardsOfSinglePlayer(playerDTO: PlayerDTO) {
        println("${playerDTO.name}: ${playerDTO.cards.joinToString(", ") { it.expression }}")
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
