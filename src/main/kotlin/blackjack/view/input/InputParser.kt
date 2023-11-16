package blackjack.view.input

object InputParser {
    private const val PLAYER_NAME_DELIMITER = ","

    fun intoPlayerNames(input: String): List<String> = input.split(PLAYER_NAME_DELIMITER)
}
