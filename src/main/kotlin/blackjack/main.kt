package blackjack

import blackjack.domain.Game
import blackjack.view.Console
import blackjack.view.DealerGameView
import blackjack.view.PlayerGameView
import blackjack.view.PlayerNameInputView
import blackjack.view.PlayerView
import blackjack.view.ResultView
import blackjack.view.WinnerView

fun main() {
    val console = Console()

    val playerNames = PlayerNameInputView(console).inputPlayerNames()
    val game = Game.start(playerNames)

    PlayerView(console).printPlayers(game.status)

    val playerGameView = PlayerGameView(console)
    game.processPlayers(playerGameView)

    val drawCount = game.processDealer()
    DealerGameView(console).printDealerDrawCount(drawCount)

    ResultView(console).printGameResult(game.status)
    WinnerView(console).printWinnerResult(game.status)
}
