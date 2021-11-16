package blackJack.view

import blackJack.dto.PlayerDto
import blackJack.dto.GamePlayersDto

object ResultView {
    fun receiveTwoCard(gamePlayersDto: GamePlayersDto) {
        println("${gamePlayersDto.getPlayerNames()} 에게 2장의 나누었습니다.")
        gamePlayersDto.toList().map {
            println("${it.name} : ${it.cards}")
        }
    }

    fun receiveCard(playerDto: PlayerDto) {
        println("${playerDto.name} :${playerDto.cards}")
    }

    fun receiveCardToDealer(playerDto: PlayerDto) {
        println("${playerDto.name}는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun noReceiveCardToDealer(playerDto: PlayerDto) {
        println("${playerDto.name}는 17이상이라 카드를 못받습니다.")
    }


    fun gameResult(playerDto: PlayerDto) {
        println("${playerDto.name}카드 :${playerDto.cards} - 결과: ${playerDto.score}")
    }
}
