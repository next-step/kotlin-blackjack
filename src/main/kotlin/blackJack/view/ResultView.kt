package blackJack.view

import blackJack.dto.PlayerDto
import blackJack.dto.PlayersDto

class ResultView {
    fun receiveTwoCard(playersDto: PlayersDto) {
        println("${playersDto.getPlayerNames()} 에게 2장의 나누었습니다.")
        playersDto.toList().map {
            println("${it.name} : ${it.cards}")
        }
    }

    fun receiveCard(playerDto: PlayerDto) {
        println("${playerDto.name} :${playerDto.cards}")
    }

    fun gameResult(playerDto: PlayerDto) {
        println("${playerDto.name}카드 :${playerDto.cards} - 결과: ${playerDto.score}")
    }
}
