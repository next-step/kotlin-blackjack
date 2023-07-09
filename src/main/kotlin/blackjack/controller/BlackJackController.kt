package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.domain.Deck
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val blackJackGame = BlackJackGame(
        Players.of(InputView.player()),
        Deck.create()
    )

    blackJackGame.start()

    ResultView.printPlayers(blackJackGame.players)
}
