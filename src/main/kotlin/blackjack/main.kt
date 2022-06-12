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

    val playerNames = PlayerNameInputView(console).run()
    val game = Game.start(playerNames)

    PlayerView(console).run(game.status)

    val playerGameView = PlayerGameView(console)
    game.processPlayers(playerGameView)

    val drawCount = game.processDealer()
    DealerGameView(console).run(drawCount)

    ResultView(console).run(game.status)
    WinnerView(console).run(game.status)
}
