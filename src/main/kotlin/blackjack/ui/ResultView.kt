package blackjack.ui

import blackjack.domain.PlayerWinType
import blackjack.ui.model.PlayerCardResult
import blackjack.ui.model.PlayerDto
import blackjack.ui.model.PlayerWinTypes

object ResultView {

    fun printCardsOf(playerDtos: List<PlayerDto>, dealerDto: PlayerDto) {
        val names = playerDtos.joinToString(", ") { it.name }
        println("${dealerDto.name}, ${names}에게 2장의 나누었습니다.")
        printCardsOfDealer(dealerDto)
        for (playerDTO in playerDtos) {
            printCardsOfSinglePlayer(playerDTO)
        }
        println()
    }

    private fun printCardsOfDealer(dealerDto: PlayerDto) {
        println("${dealerDto.name}: ${dealerDto.cards.first()}")
    }

    fun printCardsOfSinglePlayer(playerDto: PlayerDto) {
        println("${playerDto.name}: ${playerDto.cards.joinToString(", ") { it.toString() }}")
    }

    fun printInfoOfDealerBehavior(addedDealerCardNumber: Int) {
        if (addedDealerCardNumber != 0) {
            println("딜러는 16이하라 ${addedDealerCardNumber}장의 카드를 더 받았습니다.")
        }
    }

    fun printCardResults(dealerResult: PlayerCardResult, playerCardResults: List<PlayerCardResult>) {
        println()
        printSingleResult(dealerResult)
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
        playerWinTypes.forEach {
            println("${it.key}: ${it.value.description}")
        }
    }
}

private val PlayerWinType.description: String
    get() = when (this) {
        PlayerWinType.WIN -> "승"
        PlayerWinType.LOSE -> "패"
        PlayerWinType.DRAW -> "무승부"
    }
