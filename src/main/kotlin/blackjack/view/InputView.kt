package blackjack.view

import java.lang.IllegalArgumentException

object InputView {
    fun askNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",")
    }

    fun askMoreCard(name: String): Boolean {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        if (readln() == "y") {
            return true
        }
        return false
    }

    fun askBettingAmount(name: String): Int {
        println("${name}의 베팅 금액은?")
        val amount = readln().toIntOrNull() ?: throw IllegalArgumentException("베팅 금액은 숫자로 입력해야 합니다.")
        require(amount > 0) { "베팅 금액은 0보다 큰 숫자여야 합니다." }
        return amount
    }
}
