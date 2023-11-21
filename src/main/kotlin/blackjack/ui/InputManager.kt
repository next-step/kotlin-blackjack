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
    companion object {
        private const val INPUT_NOT_NULL_MESSAGE = "입력값을 입력해주세요."
        private const val INPUT_PLAYER_NAMES_MESSAGE: String = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    }
}