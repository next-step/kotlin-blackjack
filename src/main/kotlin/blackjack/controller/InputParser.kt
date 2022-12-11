package blackjack.controller

object InputParser {
    private const val INPUT_DELIMITER = ","
    private const val MINIMUM_NUMBER_OF_PLAYERS = 2

    fun parseWithDelimiter(input: String): List<String> {
        val names = input.split(INPUT_DELIMITER).map { it.trim() }
        require(names.size >= MINIMUM_NUMBER_OF_PLAYERS) { "2명 이상의 이름을 입력해주세요." }
        require(names.size == names.distinct().size) { "중복된 이름은 입력할 수 없습니다." }
        return names
    }
}
