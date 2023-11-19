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

        players.forEach {
            it.receiveCard(deck.draw())
            it.receiveCard(deck.draw())
        }

        BlackjackOutputView.printInitialCards(players)

        players.forEach {
            if (it.isFinished()) return@forEach

            do {
                val receiveCard = BlackjackInputView.readCardReceiveInput(it.name)
                if (receiveCard) {
                    it.receiveCard(deck.draw())
                }
                BlackjackOutputView.printCards(it)
            } while (receiveCard && !it.isFinished())
        }

        BlackjackOutputView.printResult(players)
    }
}
