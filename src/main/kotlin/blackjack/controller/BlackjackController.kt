package blackjack.controller

import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.view.BlackjackInputView
import blackjack.view.BlackjackOutputView

object BlackjackController {
    fun handle() {
        val namesInput = BlackjackInputView.readPlayerNamesInput()
        val players = namesInput.map { Player(it) }
        val deck = Deck()

        drawInitialCards(players, deck)

        BlackjackOutputView.printInitialCards(players)

        players.forEach {
            while (!it.isFinished() && it.isHit()) {
                it.receiveCard(deck.draw())
                BlackjackOutputView.printCards(it)
            }
        }

        BlackjackOutputView.printResult(players)
    }

    private fun drawInitialCards(players: List<Player>, deck: Deck) {
        players.forEach {
            it.receiveCard(deck.draw())
            it.receiveCard(deck.draw())
        }
    }

    private fun Player.isHit(): Boolean {
        return BlackjackInputView.readCardReceiveInput(this.name)
    }
}
