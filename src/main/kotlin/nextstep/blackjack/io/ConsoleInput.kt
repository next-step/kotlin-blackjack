package nextstep.blackjack.io

import nextstep.blackjack.dto.PlayerCardDto
import nextstep.blackjack.dto.PlayerResultDto

object ConsoleInput {
    fun inputPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",")
    }

    fun inputPlayerDraw(playerName: String): String {
        println("$playerName 은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readln()
    }
}

object ConsoleOutput {
    fun printPlayersCards(playerCardDtos: List<PlayerCardDto>) {
        println("${playerCardDtos.joinToString(", ") { it.name }}에게 ${playerCardDtos.size}장을 나누었습니다.")
        playerCardDtos.forEach {
            println("${it.name} 카드 : ${it.cardNames.joinToString(", ")}")
        }
    }

    fun printPlayerCards(playerCardDto: PlayerCardDto) {
        println("${playerCardDto.name} 카드 : ${playerCardDto.cardNames.joinToString(", ")}")
    }

    fun printPlayersResult(playerResultDtos: List<PlayerResultDto>) {
        playerResultDtos.forEach {
            println("${it.name} 카드 : ${it.cards.joinToString(", ")} - 결과: ${it.totalPoint}")
        }
    }
}
