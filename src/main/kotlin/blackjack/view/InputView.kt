package blackjack.view

object InputView {
    private const val DELIMITER = ","
    private const val GET_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"

    fun getNames(): List<String> {
        val inputStr = printMsgAndReadValue(GET_NAME) ?: throw IllegalArgumentException("")
        val result = inputStr.split(DELIMITER).map { it.trim() }
        require(result.size in 2..24) { "게임 인원은 2명 ~ 24명만 가능합니다." }
        return result
    }

    fun hitOrStand(name: String): Boolean {
        var result: String?
        do {
            result = printMsgAndReadValue("\n${name}는(은) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        } while (ContinueGame.values().none() { it.input == result })

        return ContinueGame.values().first { it.input == result }.value
    }

    private fun printMsgAndReadValue(message: String): String? {
        println(message)
        return readlnOrNull()
    }

    private enum class ContinueGame(val value: Boolean, val input: String) {
        HIT(true, "y"),
        STAND(false, "n")
    }
}
