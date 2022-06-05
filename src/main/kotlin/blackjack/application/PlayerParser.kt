package blackjack.application

object PlayerParser {
    private const val DELIMETER = ","

    fun parse(playersName: String): List<String> {

        return playersName.split(DELIMETER)
    }
}
