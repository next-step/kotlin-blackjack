package blackjack.domain.parser

object StringParser {

    fun parse(playerStr: String): List<String> {
        return playerStr.split(",").map { it.trim() }
    }

}
