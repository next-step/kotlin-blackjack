package blackjack.factory

import blackjack.player.Player

object PlayerCreator {
    private const val DELIMITER = ","

    fun create(playerNames: String): List<Player> {
        val names = playerNames.split(DELIMITER)

        return names.map(::Player)
    }
}
