package blackjack.view

object InputView {
    fun getPlayerNames(): List<String> {
        val names = readlnNotBlank().takeIf { it.contains(NAME_DELIMITER) } ?: throw IllegalArgumentException("should contain $NAME_DELIMITER")

        return names.split(NAME_DELIMITER).map { it.trim() }
    }

    fun canHit(name: String): Boolean {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

        val input = readlnNotBlank()

        require(input.contains(YES) || input.contains(NO)) { "$YES / $NO should be contained" }

        return input == YES
    }

    private fun readlnNotBlank() = readln().ifBlank { throw IllegalArgumentException("input should be not empty") }.trim()

    private const val NAME_DELIMITER = ","
    private const val YES = "y"
    private const val NO = "n"
}
