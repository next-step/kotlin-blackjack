package blackjack.application

object PlayerParser {
    private const val DELIMITER = ","

    fun parse(playersName: String): List<String> {

        return playersName.split(DELIMITER)
    }
}
