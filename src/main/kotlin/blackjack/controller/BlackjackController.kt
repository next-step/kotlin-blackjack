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
            if (it.isFinished()) return@forEach

            do {
                val isHit = BlackjackInputView.readCardReceiveInput(it.name)
                drawIfHit(it, deck, isHit)
                BlackjackOutputView.printCards(it)
            } while (isHit && !it.isFinished())
        }

        BlackjackOutputView.printResult(players)
    }

    private fun drawInitialCards(players: List<Player>, deck: Deck) {
        players.forEach {
            it.receiveCard(deck.draw())
            it.receiveCard(deck.draw())
        }
    }

    private fun drawIfHit(player: Player, deck: Deck, isHit: Boolean) {
        if (isHit) {
            player.receiveCard(deck.draw())
        }
    }
}
