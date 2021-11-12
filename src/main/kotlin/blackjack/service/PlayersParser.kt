package blackjack.service

class PlayersParser {
    fun parse(input: String): List<String> {
        return input.split(SEPARATOR)
    }

    companion object {
        private const val SEPARATOR = ","
    }
}
