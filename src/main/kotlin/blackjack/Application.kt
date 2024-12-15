package blackjack

import blackjack.domain.Game
import blackjack.domain.WinningCalculator
import blackjack.domain.player.Participant
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.ui.InputView
import blackjack.ui.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()
    val players = inputView.getPlayers().map { Player(it) }

    val game = Game.createGame(players)
    val printCallback: ((List<Participant>) -> Unit) = {
        it.forEach { player ->
            player.showCards()
        }
    }

    val turnCallback: ((Participant) -> String) = { player ->
        if (player.name == Dealer.DEALER_NAME) {
            resultView.printDealerDrawExtra()
            ""
        } else {
            inputView.setUserAnswer(player.name)
            inputView.getUserAnswer()
        }
    }

    resultView.printStartMessage(game.players)
    game.startGame(printCallback, turnCallback)
    WinningCalculator.calculatorGameResult(game.players, game.dealer)
    resultView.printGameResult(game.players, game.dealer)
}
