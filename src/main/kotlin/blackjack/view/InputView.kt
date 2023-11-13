package blackjack.view

import blackjack.domain.Money

object InputView {

    fun inputPlayerName(): List<String> {
        println(INPUT_PLAYER_NAME)
        return readln().split(DEFAULT_DELIMITER).map { it.trim() }
    }

    fun inputBetMoney(name: String): Money {
        println("$name$INPUT_BET_MONEY")
        val value = readln()
        runCatching { require(value.toInt() > 0) }
            .onFailure {
                println(NOT_FOUND_BET_MONEY)
                inputBetMoney(name)
            }
        return Money(value.toInt())
    }

    fun inputHitOrStand(name: String): Boolean {
        println("$name$INPUT_HIT_OR_STAND")
        val value = readln().lowercase()
        runCatching { require(value == YES || value == NO) }
            .onFailure {
                println(NOT_FOUND_YES_OR_NO)
                inputHitOrStand(name)
            }
        return yesOrNo(value)
    }

    private fun yesOrNo(input: String): Boolean {
        return input == YES
    }

    private const val INPUT_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val DEFAULT_DELIMITER = ","
    private const val INPUT_HIT_OR_STAND = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val YES = "y"
    private const val NO = "n"
    private const val NOT_FOUND_YES_OR_NO = "$YES 또는 ${NO}을 입력해주세요."
    private const val INPUT_BET_MONEY = "의 배팅 금액은?"
    private const val NOT_FOUND_BET_MONEY = "배팅 금액은 0보다 커야 합니다."
}
