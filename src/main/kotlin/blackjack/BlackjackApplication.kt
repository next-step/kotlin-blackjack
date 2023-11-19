package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.RandomDeck
import blackjack.ui.InputView
import blackjack.ui.ResultView

fun main() {
    val inputNames = InputView.inputNames()
    val players = Players.init(Dealer(), inputNames)

    val deck = RandomDeck.from()
    players.initCard(deck)
    ResultView.printInitPlayers(players)

    players.players
        .forEach { play(it, deck) }
    dealerPlay(players.dealer, deck)
    ResultView.printCardResult(players)
    ResultView.printGameResult(players)
}

private fun play(player: Player, deck: Deck) {
    while (player.canHit()) {
        hitOrStand(player, deck)
    }
}

private fun hitOrStand(player: Player, deck: Deck) {
    when (InputView.inputHitOrStand(player)) {
        true -> {
            val card = deck.hit()
            player.hit(card)
            ResultView.printPlayerNameAndCard(player)
        }

        false -> player.stand()
    }
}

private fun dealerPlay(dealer: Dealer, deck: Deck) {
    while (dealer.canHit()) {
        val card = deck.hit()
        dealer.hit(card)
        ResultView.printDealerHitMessage()
    }
}
