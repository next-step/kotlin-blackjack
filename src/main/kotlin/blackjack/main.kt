package blackjack

import blackjack.convert.convertView
import blackjack.domain.game.BlackjackGame
import blackjack.domain.game.GameEvent
import blackjack.domain.view.InputView
import blackjack.domain.view.ResultView

fun main() {
    val blackJackGame = BlackjackGame(playerNames = InputView.readPlayers().playerNames)

    ResultView.printStartBlackJackGame(playerViews = blackJackGame.players.convertView())

    val playerResults = blackJackGame.start(
        gameEvent = GameEvent(
            hitOrNot = { InputView.readHitOrNot(playerName = it) },
            resultEvent = { name, cards -> ResultView.printPlayerResultEvent(playerName = name, cards = cards) },
        ),
    )

    ResultView.printGameResults(playerViewResults = playerResults.convertView())
}
