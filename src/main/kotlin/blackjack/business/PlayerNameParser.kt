package blackjack.business

object PlayerNameParser {

    private const val DELIMITER = ","

    fun parse(input: String): List<String> {
        return input.split(DELIMITER)
    }
}
