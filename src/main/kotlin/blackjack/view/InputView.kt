package blackjack.view

object InputView {
    private const val INPUT_PLAYER = "참가자들의 이름을 입력해 주세요. (쉼표 기준으로 분리)"
    private const val INPUT_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
    private const val INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
    private const val ERR_INVALID_INPUT = "유효한 입력이 아닙니다. 다시 입력해주세요."

    fun readPlayers(): String {
        println(INPUT_PLAYER)
        return readValue()
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
