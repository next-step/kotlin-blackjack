package blackjack

import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.RandomDeck
import blackjack.ui.InputView
import blackjack.ui.ResultView

fun main() {
    val players = InputView.inputNames()

    val deck = RandomDeck.from()
    val initPlayers = players.initCard(deck)
    ResultView.printInitPlayers(initPlayers)

    val newPlayers = initPlayers.players
        .map { play(it, deck) }
    ResultView.printResult(Players(newPlayers))
}

private fun play(player: Player, deck: Deck): Player {
    var current = player
    while (current.canHit() && InputView.inputHitOrStand(player)) {
        current = player.hit(deck)
        ResultView.printPlayerNameAndCard(current)
    }
    return current
}
