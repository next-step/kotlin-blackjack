package blackjack

import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.RandomDeck
import blackjack.ui.InputView
import blackjack.ui.ResultView

fun main() {
    val players = InputView.inputNames()

    val deck = RandomDeck.from()
    players.initCard(deck)
    ResultView.printInitPlayers(players)

    players.players
        .forEach { play(it, deck) }
    ResultView.printResult(players)
}

private fun play(player: Player, deck: Deck) {
    while (player.canHit() && InputView.inputHitOrStand(player)) {
        player.hit(deck)
        ResultView.printPlayerNameAndCard(player)
    }
}
