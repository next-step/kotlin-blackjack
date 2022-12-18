package blackjack.view

object InputView {
    private const val INPUT_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val INPUT_HIT_OR_STAY_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val ERROR = "[ERROR] "

    fun inputName(): String {
        println(INPUT_NAME_MESSAGE)
        return readln()
    }

    fun inputHitOrStay(name: String): String {
        println(name + INPUT_HIT_OR_STAY_MESSAGE)
        return readln()
    }

    fun printError(message: String) {
        println(ERROR + message)
    }
}
