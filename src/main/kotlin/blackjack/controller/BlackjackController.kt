package blackjack.controller

import blackjack.domain.Deck
import blackjack.domain.Game
import blackjack.domain.Player
import blackjack.view.BlackjackInputView
import blackjack.view.BlackjackOutputView

object BlackjackController {
    fun handle() {
        val namesInput = BlackjackInputView.readPlayerNamesInput()
        val players = namesInput.map { Player(it) }

        val game = Game(
            players = players,
            deck = Deck(),
        )
        game.start()

        BlackjackOutputView.printInitialCards(players)
    }
}
