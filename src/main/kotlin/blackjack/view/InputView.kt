package blackjack.view

class InputView {
    fun getPlayerNames(): List<String> {
        println(INPUT_PLAYER_MESSAGE)
        val input = readln()
        return parsePlayerNamesOrThrow(input)
    }

    private fun parsePlayerNamesOrThrow(input: String?): List<String> {
        require(!input.isNullOrBlank()) { CANNOT_EMPTY_OR_NULL }
        return input.split(INPUT_PLAYER_DELIMITER).map { it.trim() }
    }

    companion object {
        private const val INPUT_PLAYER_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val INPUT_PLAYER_DELIMITER = ","
        private const val CANNOT_EMPTY_OR_NULL = "입력값은 null이거나 비어 있을 수 없습니다."
    }
}
