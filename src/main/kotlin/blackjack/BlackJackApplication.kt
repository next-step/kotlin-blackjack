package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.Deck
import blackjack.view.askHitFromPlayer
import blackjack.view.getPlayerNames
import blackjack.view.notifyDealerHit
import blackjack.view.notifyStartGame
import blackjack.view.printResult

fun main() {
    val game = BlackJackGame(Deck())
    var dealer = game.getDealer()
    val players = game.dealWith(getPlayerNames())
    notifyStartGame(dealer, players)

    val resultPlayers = game.play(players) { askHitFromPlayer(it) }
    dealer = game.playDealer(dealer) { notifyDealerHit() }

    printResult(dealer)
    resultPlayers.forEach { printResult(it) }
}
