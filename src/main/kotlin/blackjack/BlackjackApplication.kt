package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.RandomDeck
import blackjack.domain.getResult
import blackjack.ui.InputView
import blackjack.ui.ResultView

fun main() {
    val dealer = Dealer(RandomDeck.from())
    val inputNames = InputView.inputNames()
    val players = Players.init(dealer, inputNames)

    players.initCard()
    ResultView.printInitPlayers(players)

    players.players
        .forEach { play(dealer, it) }
    dealerPlay(dealer)
    ResultView.printCardResult(players)

    printGameResult(players)
}

private fun play(dealer: Dealer, player: Player) {
    while (player.canHit()) {
        hitOrStand(dealer, player)
    }
}

private fun hitOrStand(dealer: Dealer, player: Player) {
    when (InputView.inputHitOrStand(player)) {
        true -> {
            val card = dealer.draw()
            player.hit(card)
            ResultView.printPlayerNameAndCard(player)
        }

        false -> player.stand()
    }
}

private fun dealerPlay(dealer: Dealer) {
    while (dealer.canHit()) {
        val card = dealer.draw()
        dealer.hit(card)
        ResultView.printDealerHitMessage()
    }
}

private fun printGameResult(players: Players) {
    val dealer = players.dealer
    val dealerResult = players.players
        .map { dealer.getResult(it) }
        .groupingBy { it }
        .eachCount()

    val playerResult = players.players
        .associate {
            it.name to it.getResult(dealer)
        }

    ResultView.printGameResult(dealerResult, playerResult)
}
