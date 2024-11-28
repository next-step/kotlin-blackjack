package blackjack.ui

class InputView {

    val playersName: String by lazy {
        println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리")
        val input = readln()
        checkIsValidInput(input)
        input
    }

    fun checkIsValidInput(input: String) {
        require(input.isNotEmpty())
    }
}
