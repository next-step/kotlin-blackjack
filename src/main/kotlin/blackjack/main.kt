package blackjack

import blackjack.convert.convertDomain
import blackjack.convert.convertView
import blackjack.domain.game.BlackjackGame
import blackjack.domain.view.InputView
import blackjack.domain.view.ResultView
import blackjack.event.DealerEvent
import blackjack.event.GameEvent
import blackjack.event.PlayerEvent

fun main() {
    val blackJackGame = BlackjackGame(playerInfos = InputView.readPlayers().convertDomain())

    ResultView.printStartBlackJackGame(
        players = blackJackGame.players.convertView(),
        dealer = blackJackGame.dealer.convertView(),
        initHandCount = BlackjackGame.INIT_HAND_COUNT,
    )

    val playerResults = blackJackGame.start(
        gameEvent = GameEvent(
            dealerEvent = DealerEvent { ResultView.printDealerHit() },

            playerEvent = PlayerEvent(
                hitOrNot = { InputView.readHitOrNot(playerName = it) },
                resultEvent = { ResultView.printParticipantCards(participantView = it.convertView()) },
            ),
        )
    )

    ResultView.printPlayResults(participantViewResults = playerResults.convertView())
    ResultView.printMatchResults(matchResultViews = playerResults.totalMatchResult().convertView())
}
