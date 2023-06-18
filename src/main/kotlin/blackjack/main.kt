package blackjack

import blackjack.convert.convertView
import blackjack.domain.game.BlackjackGame
import blackjack.domain.view.InputView
import blackjack.domain.view.ResultView
import blackjack.event.DealerEvent
import blackjack.event.GameEvent
import blackjack.event.PlayerEvent

fun main() {
    val blackJackGame = BlackjackGame(playerNames = InputView.readPlayers().playerNames)

    ResultView.printStartBlackJackGame(
        playerViews = blackJackGame.players.convertView(),
        initHandCount = BlackjackGame.INIT_HAND_COUNT,
    )

    val playerResults = blackJackGame.start(
        gameEvent = GameEvent(
            dealerEvent = DealerEvent { ResultView.printDealerHit() },

            playerEvent = PlayerEvent(
                hitOrNot = { InputView.readHitOrNot(playerName = it) },
                resultEvent = { ResultView.printPlayerCards(playerView = it.convertView()) },
            ),
        )
    )

    ResultView.printPlayResults(playerViewResults = playerResults.convertView())
    ResultView.printMatchResults(matchResultViews = playerResults.totalMatchResult().convertView())
}
