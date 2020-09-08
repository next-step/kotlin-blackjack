package blackJack.view

import blackJack.domain.Player
import blackJack.domain.Players

object InputView {
    fun playerNames(): Players {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val inputValue = readLine()!!
        val names = checkBlank(inputValue)
        return Players(names.split(",").map { it.trim() })
    }

    fun bettingMoney(player: Player): Int {
        println("${player.name}의 배팅 금액은?")
        return changeInt(readLine()!!)
    }

    fun hitOrStay(player: Player): String {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return checkString(readLine()!!.trim())
    }

    private fun checkBlank(inputValue: String): String {
        if (inputValue.isNotBlank()) {
            return inputValue
        }
        throw IllegalArgumentException("공백 값을 입력하지 마세요")
    }

    private fun changeInt(inputValue: String): Int {
        return inputValue.toIntOrNull() ?: throw IllegalArgumentException("$inputValue 는 숫자가 아닙니다.")
    }

    private fun checkString(inputValue: String): String {
        if (inputValue == "y" || inputValue == "n") {
            return inputValue
        }
        throw IllegalArgumentException("$inputValue 요청은 없습니다. y 또는 n으로 입력해주세요")
    }
}
