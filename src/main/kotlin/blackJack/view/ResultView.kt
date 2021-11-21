package blackJack.view

import blackJack.domain.result.Results
import blackJack.dto.DealerDto
import blackJack.dto.PlayerDto
import blackJack.dto.GamePlayersDto

object ResultView {
    fun receiveTwoCard(gamePlayersDto: GamePlayersDto) {
        println("${gamePlayersDto.getPlayerNames()} 에게 2장의 나누었습니다.")
        gamePlayersDto.map {
            println("${it.name} : ${it.cards}")
        }
    }

    fun receiveCard(playerDto: PlayerDto, isContinue: Boolean) {
        if (isContinue) {
            println("${playerDto.name} :${playerDto.cards}")
        }
    }

    fun receiveCardToDealer(dealerDto: DealerDto, isContinue: Boolean) {
        if (isContinue) {
            println("${dealerDto.name}는 16이하라 한장의 카드를 더 받았습니다.")
        } else {
            println("${dealerDto.name}는 17이상이라 카드를 못받습니다.")
        }
    }

    fun playerGameResult(playerDto: PlayerDto) {
        println("${playerDto.name}카드 :${playerDto.cards} - 결과: ${playerDto.score}")
    }

    fun dealerGameResult(dealerDto: DealerDto) {
        println("${dealerDto.name}카드 :${dealerDto.cards} - 결과: ${dealerDto.score}")
    }

    fun winOrLoseView(results: Results) {
        val dealerResult = results.dealerResult
        val playerResults = results.playerResults

        println("## 최종 승패")
        println("DEALER : 승 : ${dealerResult.win}, 패 :${dealerResult.lose}, 무: ${dealerResult.draw}")
        playerResults.toList().forEach {
            println("${it.name}: ${it.winDrawLose}")
        }
    }
}
