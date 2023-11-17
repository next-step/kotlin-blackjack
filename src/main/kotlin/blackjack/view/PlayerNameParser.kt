package blackjack.view

object PlayerNameParser {

    private const val DELIMITER = ","

    fun parse(input: String): List<String> {
        return input.split(DELIMITER)
    }
}
