package blackjack.service

object InputParser {

    fun parsePlayerName(names: String): List<String> {
        return names.split(",")
    }

    fun parseMoreCard(input: String): Boolean {
        require(input.isNotEmpty() && input in "yn") { "[$input] it invalid input" }

        return input == "y"
    }
}
