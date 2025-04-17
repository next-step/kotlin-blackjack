package blackjack

import blackjack.domain.deck.Deck
import blackjack.domain.player.Name
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val rawNames = InputView.getPlayerNames()
    val players = Players(rawNames.map(::Name).map { Player(it) })
    val deck = Deck.create()

    players.initializeState { deck.drawCard() }
    println(deck)

    OutputView.printInitialCards(players)
}
