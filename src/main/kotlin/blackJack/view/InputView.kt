package blackJack.view

import blackJack.dto.PlayerDto
import java.math.BigDecimal

object InputView {

    fun inputBettingMoney(playerName: String): BigDecimal {
        println("${playerName}의 배팅 금액은?")
        return readLine()!!.toBigDecimal()
    }

    fun inputPlayersName(): String {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readLine()!!
    }

    fun doYouWantCardView(playerDto: PlayerDto): Boolean {
        println("${playerDto.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readLine()) {
            "y" -> true
            "n" -> false
            else -> {
                println("y나 n만 가능합니다")
                return doYouWantCardView(playerDto)
            }
        }
    }
}
