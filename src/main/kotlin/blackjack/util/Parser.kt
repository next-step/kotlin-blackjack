package blackjack.util

object Parser {
    private const val DELIMITER = ","

    fun parse(rawString: String): List<String> {
        return rawString.split(DELIMITER)
    }
}
