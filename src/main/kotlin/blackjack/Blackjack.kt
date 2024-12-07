package blackjack

import blackjack.view.InputView
import blackjack.view.ResultView

class Blackjack {
    fun start() {
        val inputView = InputView()
        val resultView = ResultView()
        val playerNamesInput = inputView.getPlayerNamesInput()
        val playersNames = playerNamesInput.split()
        val players = playersNames.map { Player(name = it) }
        resultView.renderPlayerInitOutput(playersNames)
        for (player in players) {
            resultView.renderPlayerCardsOutput(player.name, player.cards.toString())
        }

        for (player in players) {
            while (true) {
                val answerInput = inputView.getPlayerRequestInput(player.name)
                if (answerInput == "n") break
                player.takeNewCard()
                resultView.renderPlayerCardsOutput(player.name, player.cards.toString())
            }
        }

        for (player in players) {
            resultView.renderPlayerCardsResultOutput(player.name, player.cards.toString(), player.calculateResult())
        }
    }
}