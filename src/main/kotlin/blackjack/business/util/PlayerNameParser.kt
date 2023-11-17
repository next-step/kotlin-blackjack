package blackjack.business.util

object PlayerNameParser {

    private const val DELIMITER = ","

    fun parse(input: String): List<String> {
        return input.split(DELIMITER)
    }
}
