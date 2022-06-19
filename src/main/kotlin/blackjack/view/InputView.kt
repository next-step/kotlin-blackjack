package blackjack.view

class InputView {
    fun getNames(): List<String> {
        val inputStr = printMsgAndReadValue(GET_NAME) ?: throw IllegalArgumentException("")
        val result = inputStr.split(DELIMITER).map { it.trim() }
        require(result.size in 2..24) { "게임 인원은 2명 ~ 24명만 가능합니다." }
        return result
    }

    private fun printMsgAndReadValue(message: String): String? {
        println(message)
        return readlnOrNull()
    }

    companion object {
        const val DELIMITER = ","
        const val GET_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    }
}
