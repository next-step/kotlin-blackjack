package blackjack.controller

import blackjack.domain.Game
import blackjack.view.InputView
import blackjack.view.ResultView

object BlackjackController {
    fun start() {
        val playerNames = InputView.inputPlayerNames().split(",").map { it.trim() }
        val game = Game(playerNames)

        ResultView.printSplitCardResult(game.dealer, game.players)
        ResultView.printDealerInitialCard(game.dealer)

        game.players.forEach { ResultView.printPlayerCards(listOf(it.name to it.cards)) }

        game.players.forEach { player ->
            game.handlePlayerTurn(player) { playerName ->
                InputView.inputPickCard(playerName)
            }
            ResultView.printPlayerCards(listOf(player.name to player.cards))
        }

        val dealerDraws = game.handleDealerTurn()
        if (dealerDraws) {
            ResultView.printDealerDrawMessage(true)
        } else {
            ResultView.printDealerDrawMessage(false)
        }

        ResultView.printFinalScores(game.players, game.dealer)

        val results = game.determineResults()
        val dealerResult = game.calculateDealerResult(results)
        ResultView.printGameResult(results, dealerResult)
    }
}

