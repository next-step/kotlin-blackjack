package blackjack.service

class Parser {
    fun parse(input: String): List<String> {
        return input.split(SEPARATOR)
    }

    companion object {
        private const val SEPARATOR = ","
    }
}
