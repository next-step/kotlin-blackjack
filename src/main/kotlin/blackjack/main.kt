package blackjack

import blackjack.domain.Game
import blackjack.view.Console
import blackjack.view.DealerGameView
import blackjack.view.PlayerGameView
import blackjack.view.PlayerInputView
import blackjack.view.PlayerView
import blackjack.view.ResultView
import blackjack.view.WinnerView

fun main() {
    val console = Console()

    val playerNames = PlayerInputView(console).inputPlayerInfo()
    val game = Game.start(playerNames)

    PlayerView(console).printPlayers(game.status)

    val playerGameView = PlayerGameView(console)
    game.drawPlayerCard(playerGameView)

    val drawCount = game.drawDealerCard()
    DealerGameView(console).printDealerDrawCount(drawCount)

    ResultView(console).printGameResult(game.status)
    WinnerView(console).printWinnerResult(game.status)
}
