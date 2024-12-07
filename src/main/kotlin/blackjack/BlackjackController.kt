package blackjack

class BlackjackController {
    fun createPlayers(playerNamesInput: String): Players {
        val playersNames = playerNamesInput.split()
        val players = Players.from(playersNames.map(::Player))
        return players
    }

    fun checkPlayerRequest(
        answerInput: String,
        player: Player,
    ): Boolean =
        when (answerInput.lowercase()) {
            REFUSE_INPUT -> true
            ACCEPT_INPUT -> {
                player.takeNewCard()
                false
            }

            else -> throw IllegalArgumentException("Invalid input: '$answerInput'. Please enter 'y' or 'n'.")
        }

    private fun String.split(delimiter: String = ","): List<String> {
        if (this.isBlank()) return emptyList()

        val regex = Regex.escape(delimiter).toRegex()
        return this.split(regex)
            .map { it.trim() }
            .filter { it.isNotEmpty() }
    }

    private companion object {
        const val REFUSE_INPUT = "n"
        const val ACCEPT_INPUT = "y"
    }
}
