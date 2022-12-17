package blackjack.view

object InputView {
    private const val RETRY = "y"
    private const val RETRY_NOT = "n"
    private const val RETRY_INPUT_EXCEPTION = "y 혹은 n을 입력해야만해요."

    fun readName(): String {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln()
    }

    fun retryOrNot(name: String): Boolean {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readln().apply { validateRetryInput(this) } == RETRY
    }

    private fun validateRetryInput(input: String) {
        require(input in listOf(RETRY, RETRY_NOT)) { RETRY_INPUT_EXCEPTION }
    }
}
