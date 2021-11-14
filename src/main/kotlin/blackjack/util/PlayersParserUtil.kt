package blackjack.util

object PlayersParserUtil {

    private const val SEPARATOR = ","

    fun parse(input: String): List<String> {
        return input.split(SEPARATOR)
    }
}
