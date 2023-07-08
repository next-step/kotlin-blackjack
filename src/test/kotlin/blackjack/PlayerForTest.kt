package blackjack

import domain.player.Player
import domain.player.Players

val playersWithOnePlayer: Players get() = Players(listOf(Player("peter")))
val playersWithTwoPlayer: Players get() = Players(listOf(Player("peter"), Player("승현")))
