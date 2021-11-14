package blackJack.view

import blackJack.dto.PlayerDto

object InputView {

    fun inputPlayersName(): String {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readLine()!!
    }

    fun doYouWantCardView(playerDto: PlayerDto): Boolean {
        println("${playerDto.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readLine()!!) {
            "y" -> true
            else -> false
        }
    }
}
