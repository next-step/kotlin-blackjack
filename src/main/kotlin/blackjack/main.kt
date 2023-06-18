package blackjack

import blackjack.convert.convertView
import blackjack.domain.game.BlackjackGame
import blackjack.domain.view.InputView
import blackjack.domain.view.ResultView
import blackjack.event.GameEvent

fun main() {
    val blackJackGame = BlackjackGame(playerNames = InputView.readPlayers().playerNames)

    ResultView.printStartBlackJackGame(
        playerViews = blackJackGame.players.convertView(),
        initHandCount = BlackjackGame.INIT_HAND_COUNT,
    )

    val playerResults = blackJackGame.start(
        gameEvent = GameEvent(
            hitOrNot = { InputView.readHitOrNot(playerName = it) },
            resultEvent = { ResultView.printPlayerCards(playerView = it.convertView()) },
        ),
    )

    ResultView.printGameResults(playerViewResults = playerResults.convertView())
}
