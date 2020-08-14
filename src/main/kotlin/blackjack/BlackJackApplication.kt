package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.view.askHitFromPlayer
import blackjack.view.getPlayerNames
import blackjack.view.notifyStartGame
import blackjack.view.printResult

fun main() {
    val dealer = Dealer()
    val game = BlackJackGame(Deck())
    val players = game.dealWith(getPlayerNames())
    notifyStartGame(players)

    val resultPlayers = game.play(players) { askHitFromPlayer(it) }
    resultPlayers.forEach { printResult(it) }
}
