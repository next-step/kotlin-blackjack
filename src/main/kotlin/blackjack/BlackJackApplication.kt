package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.card.Deck
import blackjack.domain.card.RandomDrawStrategy
import blackjack.domain.player.Dealer
import blackjack.view.askHitFromPlayer
import blackjack.view.getBettingMoney
import blackjack.view.getPlayerNames
import blackjack.view.notifyDealerHit
import blackjack.view.notifyStartGame
import blackjack.view.printCardResults
import blackjack.view.printPlayerCards
import blackjack.view.printPlayerResults

fun main() {
    val game = BlackJackGame(Deck(RandomDrawStrategy()))
    var dealer = game.getDealer()
    val players = game.dealWith(getPlayerNames()) { getBettingMoney(it) }
    notifyStartGame(dealer, players)

    val resultPlayers = game.play(
        players,
        isAgreedHit = { askHitFromPlayer(it) },
        printPlayerCards = { printPlayerCards(it) }
    )
    dealer = game.playDealer(dealer) { notifyDealerHit() }

    printCardResults(dealer, resultPlayers)
    dealer as Dealer
    printPlayerResults(dealer.createPlayerResults(resultPlayers))
}
