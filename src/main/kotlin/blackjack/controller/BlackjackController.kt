package blackjack.controller

import blackjack.domain.Game
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

object BlackjackController {
    fun start() {
        val game = initializeGame()
        playPlayerTurns(game)
        playDealerTurn(game)
        displayGameResults(game)
    }

    private fun initializeGame(): Game {
        val playerNames = InputView.inputPlayerNames().split(",").map { it.trim() }
        val game = Game(playerNames)

        ResultView.printSplitCardResult(game.dealer, game.players)
        ResultView.printDealerInitialCard(game.dealer)

        displayInitialPlayerCards(game)

        return game
    }

    private fun displayInitialPlayerCards(game: Game) {
        game.players.forEach {
            ResultView.printPlayerCards(listOf(it.name to it.cards))
        }
    }

    private fun playPlayerTurns(game: Game) {
        game.players.forEach { player ->
            playPlayerTurn(game, player)
        }
    }

    private fun playPlayerTurn(game: Game, player: Player) {
        game.handlePlayerTurn(player) { playerName ->
            InputView.inputPickCard(playerName)
        }
        ResultView.printPlayerCards(listOf(player.name to player.cards))
    }

    private fun playDealerTurn(game: Game) {
        val dealerDraws = game.handleDealerTurn()

        if (dealerDraws) {
            ResultView.printDealerDrawMessage(true)
        } else {
            ResultView.printDealerDrawMessage(false)
        }

        ResultView.printFinalScores(game.players, game.dealer)
    }

    private fun displayGameResults(game: Game) {
        val results = game.determineResults()
        val dealerResult = game.calculateDealerResult(results)
        ResultView.printGameResult(results, dealerResult)
    }
}

