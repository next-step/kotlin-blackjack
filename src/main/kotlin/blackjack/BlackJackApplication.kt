package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.State
import blackjack.view.askFitFromPlayer
import blackjack.view.getPlayerNames
import blackjack.view.notifyStartGame
import blackjack.view.printPlayerCards
import blackjack.view.printResult

fun main() {
    val game = BlackJackGame(Deck())
    val players = game.dealWith(getPlayerNames())
    notifyStartGame(players)

    val resultPlayers = mutableListOf<Player>()
    players.forEach {
        var player = it
        while (player.state is State.Playing) {
            player = game.askHit(player, askFitFromPlayer(player))
            if (player.state !is State.Playing) resultPlayers.add(player)
            printPlayerCards(player)
        }
    }
    resultPlayers.forEach { printResult(it) }
}
