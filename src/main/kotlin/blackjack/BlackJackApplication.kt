package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.Deck
import blackjack.domain.Players
import blackjack.domain.State
import blackjack.view.askFitFromPlayer
import blackjack.view.getPlayerNames
import blackjack.view.notifyStartGame
import blackjack.view.printPlayerCards
import blackjack.view.printResult

fun main() {
    val players = Players.from(getPlayerNames())
    val game = BlackJackGame(Deck())
    game.deal(players)
    notifyStartGame(players)

    players.forEach {
        while (it.state is State.Playing) {
            game.askHit(it, askFitFromPlayer(it))
            printPlayerCards(it)
        }
    }
    players.forEach { printResult(it) }
}
