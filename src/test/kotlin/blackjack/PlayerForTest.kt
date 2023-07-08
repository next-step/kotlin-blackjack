package blackjack

import domain.gamer.player.Player
import domain.gamer.player.Players

val playersWithOnePlayer: Players get() = Players(listOf(Player("peter")))
val playersWithTwoPlayer: Players get() = Players(listOf(Player("peter"), Player("승현")))
