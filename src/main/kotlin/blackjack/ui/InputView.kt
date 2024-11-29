package blackjack.ui

class InputView {

    private val playersName: String by lazy {
        println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리")
        val input = readln()
        checkIsValidInput(input)
        input
    }

    fun getPlayers(): List<String> {
        return playersName.replace(" ", "").split(DELIMITER)
    }

    fun checkIsValidInput(input: String) {
        require(input.isNotEmpty())
    }

    companion object {
        private const val DELIMITER = ","
    }
}
