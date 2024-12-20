package blackjack

import blackjack.domain.Game
import blackjack.domain.WinningCalculator
import blackjack.domain.player.Participant
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.ui.InputView
import blackjack.ui.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()
    val players = Players(inputView.getPlayers().map { Player(it) })

    val game = Game.createGame(players)
    val printCallback: ((Participant) -> Unit) = { player ->
        resultView.showCards(player)
    }

    val turnCallback: ((Participant) -> String) = { player ->
        resultView.printDealerDrawExtra(player)
        inputView.setUserAnswer(player.name)
        inputView.getUserAnswer()
    }

    resultView.printStartMessage(players)
    game.startGame(printCallback, turnCallback)
    WinningCalculator.calculatorGameResult(game.players, game.dealer)
    resultView.printGameResult(game.players, game.dealer)
}
