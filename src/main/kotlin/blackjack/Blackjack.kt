package blackjack

import blackjack.model.Player
import blackjack.model.Players
import blackjack.view.InputView
import blackjack.view.ResultView

class Blackjack {
    val inputView = InputView()
    val resultView = ResultView()
    val blackjackController = BlackjackController()

    fun start() {
        val players = initPlayers()

        processPlayerTurns(players)

        renderResults(players)
    }

    private fun renderResults(players: Players) {
        for (player in players.players) {
            resultView.renderPlayerCardsResultOutput(player.name, player.cards.toString(), player.calculateResult())
        }
    }

    private fun processPlayerTurns(players: Players) {
        for (player in players.players) {
            processSinglePlayerTurn(player)
        }
    }

    private fun processSinglePlayerTurn(player: Player) {
        var continueTurn = true
        while (continueTurn) {
            val answerInput = inputView.getPlayerRequestInput(player.name)
            if (blackjackController.checkPlayerRequest(answerInput, player)) {
                continueTurn = false
            } else {
                resultView.renderPlayerCardsOutput(player.name, player.cards.toString())
            }
        }
    }

    private fun initPlayers(): Players {
        val playerNamesInput = inputView.getPlayerNamesInput()
        val players = blackjackController.createPlayers(playerNamesInput)
        showPlayersInfo(players)
        return players
    }

    private fun showPlayersInfo(players: Players) {
        resultView.renderPlayerInitOutput(players.getPlayerNames())
        for (player in players.players) {
            resultView.renderPlayerCardsOutput(player.name, player.cards.toString())
        }
    }
}
