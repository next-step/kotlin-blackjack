package blackjack.domain

import java.util.regex.Pattern

private const val SPLIT_CHARACTER = "."
private const val REGULAR_EXPRESSION = "^[a-z|A-Z][a-z|A-Z,]*[a-z|A-Z]$"

class BlackjackGame(playerNames: String) {
    private var players: List<Player> = emptyList()

    init {
        players = parsingPlayers(playerNames)
    }

    private fun parsingPlayers(playerNames: String): List<Player> {
        require(Pattern.compile(REGULAR_EXPRESSION).matcher(playerNames).matches())
        val players = mutableListOf<Player>()
        playerNames.split(SPLIT_CHARACTER).forEach { players.add(Player(it)) }
        return players.toList()
    }
}
