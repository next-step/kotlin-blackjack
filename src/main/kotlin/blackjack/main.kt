package blackjack

import blackjack.domain.game.BlackJackGame
import blackjack.domain.game.GameEvent
import blackjack.domain.view.InputView
import blackjack.domain.view.ResultView

fun main() {
    val blackJackGame = BlackJackGame(playerNames = InputView.readPlayers().playerNames)

    ResultView.printStartBlackJackGame(players = blackJackGame.players)

    blackJackGame.start(
        gameEvent = GameEvent(
            hitOrNot = { InputView.readHitOrNot(playerName = it) },
            resultEvent = { ResultView.printPlayerCards(player = it) },
        ),
    )

    ResultView.printGameResults(players = blackJackGame.players)
}
