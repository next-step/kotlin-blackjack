package blackjack.domain.parser

import blackjack.domain.participant.Player

object PlayerParser {

    fun parse(playerStr: String): List<Player> {
        return playerStr.split(",").map { it.trim().toPlayer() }
    }

    private fun String.toPlayer() = Player(this)

}
