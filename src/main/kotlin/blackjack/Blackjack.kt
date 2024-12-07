package blackjack

import blackjack.view.InputView
import blackjack.view.ResultView

class Blackjack {
    val inputView = InputView()
    val resultView = ResultView()
    val blackjackController = BlackjackController()

    fun start() {
        val players = initPlayers()

        processPlayerTurns(players)

        renderPlayerResults(players)
    }

    private fun renderPlayerResults(players: Players) {
        for (player in players.getPlayers()) {
            resultView.renderPlayerCardsResultOutput(player.name, player.cards.toString(), player.calculateResult())
        }
    }

    private fun processPlayerTurns(players: Players) {
        for (player in players.getPlayers()) {
            while (true) {
                val answerInput = inputView.getPlayerRequestInput(player.name)
                if (blackjackController.checkPlayerRequest(answerInput, player)) break
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
        for (player in players.getPlayers()) {
            resultView.renderPlayerCardsOutput(player.name, player.cards.toString())
        }
    }
}
