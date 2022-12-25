package blackjack.view

object InputView {
    private const val MINIMUM_BETTING_MONEY = 0
    private const val RETRY = "y"
    private const val RETRY_NOT = "n"
    private const val RETRY_INPUT_EXCEPTION = "y 혹은 n을 입력해야만해요."
    private const val BETTING_MONEY_EXCEPTION = "0보다 큰 금액을 입력해야만해요."

    fun readName(): String {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln()
    }

    fun retryOrNot(name: String): Boolean {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readln().apply { validateRetryInput(this) } == RETRY
    }

    fun readBettingMoney(name: String): Long {
        println()
        println("${name}의 배팅 금액은?")
        return readln().toLong().apply { validateBettingMoney(this) }
    }

    private fun validateRetryInput(input: String) {
        require(input in listOf(RETRY, RETRY_NOT)) { RETRY_INPUT_EXCEPTION }
    }

    private fun validateBettingMoney(input: Long) {
        require(input > MINIMUM_BETTING_MONEY) { BETTING_MONEY_EXCEPTION }
    }
}
