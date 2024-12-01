package blackjack.domain.parser

import blackjack.domain.Player

object PlayerParser {

    fun parse(playerStr: String): List<Player> {
        return playerStr.split(",").map { it.trim().toPlayer() }
    }

    private fun String.toPlayer() = Player(this)

}
