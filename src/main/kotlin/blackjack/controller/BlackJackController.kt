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
    val canReceivePlayers = blackJackGame.canReceivePlayers()

    canReceivePlayers.forEach { player ->
        while (InputView.getMoreCard(player.name)) {
            blackJackGame.draw(player) { ResultView.printPlayer(it) }
        }
        ResultView.printPlayer(player)
    }
    ResultView.printResult(blackJackGame.players)
}
