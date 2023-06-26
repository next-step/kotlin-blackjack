package next.step.blackjack.view

import next.step.blackjack.domain.player.PlayerName
import next.step.blackjack.domain.player.PlayerNames

object PlayerNameParser {

    fun parse(str: String): PlayerNames = PlayerNames.of(
        str.split(",")
            .map { PlayerName.of(it.trim()) }
            .toSet()
    )
}
