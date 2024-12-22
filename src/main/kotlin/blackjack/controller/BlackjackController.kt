package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.player.Player
import blackjack.domain.state.GameResult
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {
    private val game = BlackjackGame()

    fun run() {
        val players = initializePlayers()
        game.start(players)
        OutputView.printInitialCards(game.dealer, players)

        playAllTurns(players)
        playDealerTurn()

        showResults(players)
    }

    private fun initializePlayers(): List<Player> {
        val names = InputView.readNames()
        return names.map { Player.of(it) }
    }

    private fun playAllTurns(players: List<Player>) {
        players.forEach { player ->
            playTurn(player)
        }
    }

    private fun playTurn(player: Player) {
        while (player.isRunning() && InputView.readMoreCard(player.toString())) {
            player.drawCard(game.drawCard())
            OutputView.printPlayerCards(player)
        }
    }

    private fun playDealerTurn() {
        if (game.dealer.needsMoreCard()) {
            game.dealer.drawCard(game.drawCard())
            OutputView.printDealerDrawMessage()
        }
    }

    private fun showResults(players: List<Player>) {
        OutputView.printFinalCards(game.dealer, players)

        val gameResults = game.calculateResult()
        val dealerResults = calculateDealerResults(gameResults)

        OutputView.printFinalResults(dealerResults, gameResults)
    }

    private fun calculateDealerResults(playerResults: Map<Player, GameResult>): Map<GameResult, Int> {
        val dealerWins = playerResults.count { it.value == GameResult.LOSE }
        val dealerLoses = playerResults.count { it.value == GameResult.WIN }
        return mapOf(
            GameResult.WIN to dealerWins,
            GameResult.LOSE to dealerLoses
        )
    }
}
