package blackjack.ui

class InputView {
    private val playersName: String by lazy {
        println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)")
        val input = readln()
        checkIsValidInput(input)
        input
    }
    private lateinit var answer: String

    fun getPlayers(): List<String> {
        return playersName.replace(" ", "").split(DELIMITER)
    }

    fun checkIsValidInput(input: String) {
        require(input.isNotEmpty())
    }

    fun setUserAnswer(name: String) {
        println("${name}은/는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val input = readln()
        checkIsValidInput(input)
        answer = input
    }

    fun getUserAnswer(): String {
        return answer
    }

    companion object {
        private const val DELIMITER = ","
    }
}
