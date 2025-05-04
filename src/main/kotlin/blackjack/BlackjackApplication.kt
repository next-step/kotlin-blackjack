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
    OutputView.printInitialCards(players)

    players.values.forEach { player -> player.takeTurn(deck) }
    OutputView.printBlackjackResult(players)
}

private fun Player.takeTurn(deck: Deck) {
    while (this.canDraw && InputView.getUserChoice(this)) {
        this.draw(deck.drawCard())
        OutputView.printPlayerCards(this)

        if (!this.canDraw) {
            return OutputView.announceBust(this)
        }
    }
}
