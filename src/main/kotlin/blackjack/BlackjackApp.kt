package blackjack

import blackjack.application.Game
import blackjack.application.PlayerParser
import blackjack.ui.BlackjackInputView
import blackjack.ui.BlackjackResultView

object BlackjackApp {
    fun launch() {
        val playerNamesStr = BlackjackInputView.queryAboutPlayer()
        val playerNames = PlayerParser.parse(playerNamesStr)
        val game = Game(playerNames)

        BlackjackResultView.resultOfInitialDraw(game.players)

        game.start()

        BlackjackResultView.resultOfGame(game.players)
    }
}

fun main() {
    BlackjackApp.launch()
}
