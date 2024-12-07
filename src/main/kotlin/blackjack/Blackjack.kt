package blackjack

import blackjack.view.InputView
import blackjack.view.ResultView

class Blackjack {
    val inputView = InputView()
    val resultView = ResultView()

    fun start() {
        val playerNamesInput = inputView.getPlayerNamesInput()
        val players = initPlayers(playerNamesInput)
        resultView.renderPlayerInitOutput(players.getPlayerNames())
        for (player in players.getPlayers()) {
            resultView.renderPlayerCardsOutput(player.name, player.cards.toString())
        }

        for (player in players.getPlayers()) {
            while (true) {
                val answerInput = inputView.getPlayerRequestInput(player.name)
                if (answerInput == "n") break
                player.takeNewCard()
                resultView.renderPlayerCardsOutput(player.name, player.cards.toString())
            }
        }

        for (player in players.getPlayers()) {
            resultView.renderPlayerCardsResultOutput(player.name, player.cards.toString(), player.calculateResult())
        }
    }

    private fun initPlayers(playerNamesInput: String): Players {
        val playersNames = playerNamesInput.split()
        val players = Players.from(playersNames.map(::Player))
        return players
    }
}