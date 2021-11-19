package view

object InputView {
    fun askPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readInput().filterNot { it.isWhitespace() }
            .split(NAME_DELIMITER)
    }

    fun askDraw(name: String): Boolean {
        println("\n${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readInput().lowercase() == YES
    }

    fun askBetAmount(name: String): Int {
        println("\n${name}의 배팅 금액은?")
        return readInput().toInt()
    }

    private fun readInput(): String {
        val input = readLine()
        require(!input.isNullOrBlank()) { "빈 값을 입력할 수는 없습니다." }
        return input
    }

    private const val NAME_DELIMITER = ","
    private const val YES = "y"
}
