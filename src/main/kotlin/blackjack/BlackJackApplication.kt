package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.Deck
import blackjack.domain.PlayerResult
import blackjack.domain.RandomDrawStrategy
import blackjack.view.askHitFromPlayer
import blackjack.view.getPlayerNames
import blackjack.view.notifyDealerHit
import blackjack.view.notifyStartGame
import blackjack.view.printPlayerCards
import blackjack.view.printPlayerResults
import blackjack.view.printResult

fun main() {
    val game = BlackJackGame(Deck(RandomDrawStrategy()))
    var dealer = game.getDealer()
    val players = game.dealWith(getPlayerNames())
    notifyStartGame(dealer, players)

    val resultPlayers = game.play(
        players,
        isAgreedHit = { askHitFromPlayer(it) },
        printPlayerCards = { printPlayerCards(it) }
    )
    dealer = game.playDealer(dealer) { notifyDealerHit() }

    printResult(dealer)
    resultPlayers.forEach { printResult(it) }

    printPlayerResults(PlayerResult.ofDealer(dealer, resultPlayers), PlayerResult.ofChallengers(dealer, resultPlayers))
}
