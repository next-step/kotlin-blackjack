package blackjack.ui

import blackjack.domain.PlayerWinType
import blackjack.ui.model.PlayerCardResult
import blackjack.ui.model.PlayerDto
import blackjack.ui.model.PlayerWinTypes

object ResultView {

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

    fun printInfoOfDealerCard() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        println()
    }

    fun printCardResults(playerCardResults: List<PlayerCardResult>) {
        for (blackJackResult in playerCardResults) {
            printSingleResult(blackJackResult)
        }
        println()
    }

    private fun printSingleResult(playerCardResult: PlayerCardResult) {
        println("${playerCardResult.name}: ${playerCardResult.cardNames.joinToString()} - 결과: ${playerCardResult.point}")
    }

    fun printWinningResult(playerWinTypes: PlayerWinTypes) {
        println("## 최종 승패")
        println(playerWinTypes.dealerResult)
        playerWinTypes.keys.forEach {
            println("$it: ${playerWinTypes[it]?.description}")
        }
    }
}

private val PlayerWinType.description: String
    get() = when (this) {
        PlayerWinType.WIN -> "승"
        PlayerWinType.LOSE -> "패"
        PlayerWinType.DRAW -> "무승부"
    }
