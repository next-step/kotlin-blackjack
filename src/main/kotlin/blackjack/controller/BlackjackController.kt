package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.player.Player
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
        val betAmounts = InputView.readBetAmounts(names)

        return names.zip(betAmounts).map { (name, betAmount) ->
            Player.of(name, betAmount)
        }
    }

    private fun playAllTurns(players: List<Player>) {
        players.forEach { player ->
            playTurn(player)
            println(player)
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

        val playerProfits = game.calculateProfits(players, game.dealer)
        val dealerProfit = game.calculateDealerProfit(players, game.dealer)

        OutputView.printFinalProfits(dealerProfit, playerProfits)
    }
}
