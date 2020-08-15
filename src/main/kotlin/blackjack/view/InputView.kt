package blackjack.view

import blackjack.domain.player.Player

object InputView {
    const val YES = "y"
    const val NO = "n"

    private const val INPUT_PLAYER = "참가자들의 이름을 입력해 주세요. (쉼표 기준으로 분리)"
    private const val INPUT_MODE_CARD = "- 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)"
    private const val ERR_INVALID_INPUT = "유효한 입력이 아닙니다. 다시 입력해주세요."
    private const val ERR_INVALID_NAME = "1명 이상의 이름을 입력해주세요."

    fun readPlayers(): String {
        println(INPUT_PLAYER)
        return readValue(errMsg = ERR_INVALID_NAME)
    }

    fun readMoreCard(player: Player): String {
        println("${player.name} $INPUT_MODE_CARD")
        return readValue { it == YES || it == NO }
    }

    private fun readValue(
        errMsg: String = ERR_INVALID_INPUT,
        isValid: (value: String) -> Boolean = { true }
    ): String {
        var input = readLine()
        while (input.isNullOrBlank() || !isValid(input)) {
            println(errMsg)
            input = readLine()
        }

        return input
    }
}
