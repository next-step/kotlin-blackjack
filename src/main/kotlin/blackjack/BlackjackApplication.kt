package blackjack

import blackjack.model.CardDeck
import blackjack.service.BlackjackGame
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackApplication {
    fun play() {
        val deck = CardDeck.defaultDeck()
        val players = InputView.readPlayers()
        val blackjackGame = BlackjackGame(deck, players)

        OutputView.printInitCards(blackjackGame.players)

        blackjackGame.play(
            InputView.readPlayerPickAnswer,
            OutputView.printPlayerCards
        )

        OutputView.printResult(players)
    }
}

fun main() {
    BlackjackApplication().play()
}
