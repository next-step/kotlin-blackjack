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
    while (!player.isBust() && InputView.inputHitOrStand(player)) {
        val card = deck.hit()
        player.hit(card)
        ResultView.printPlayerNameAndCard(player)
    }
}
