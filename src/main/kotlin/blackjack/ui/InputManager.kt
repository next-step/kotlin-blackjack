package blackjack.ui

class InputManager {
    fun inputPlayerNames(): List<String> {
        println(INPUT_PLAYER_NAMES_MESSAGE)
        return inputUserValue().replace("\\s".toRegex(), "").split(",")
    }

    private fun inputUserValue(): String {
        val input = readln()
        require(input.isNotBlank()) { INPUT_NOT_NULL_MESSAGE }
        return input.trim()
    }

    fun inputShouldDrawCard(name: String): Int {
        println("${name}는 $INPUT_SHOULD_DRAW_CARD_MESSAGE")
        return when (readln()) {
            "Y", "y" -> 1
            "N", "n" -> 0
            else -> 0
        }
    }

    companion object {
        private const val INPUT_NOT_NULL_MESSAGE = "입력값을 입력해주세요."
        private const val INPUT_PLAYER_NAMES_MESSAGE: String = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val INPUT_SHOULD_DRAW_CARD_MESSAGE: String = "한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    }
}
