package next.step.blackjack.view

import next.step.blackjack.domain.Player

object PlayerParser {

    fun parse(str: String): Set<Player> = str.split(",")
        .map { Player.of(it.trim()) }
        .toSet()
}