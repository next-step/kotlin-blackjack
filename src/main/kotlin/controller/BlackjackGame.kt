package controller

import model.Card
import model.Name
import model.Players
import view.InputView
import view.ResultView

class BlackjackGame {
    private val inputView = InputView()
    private val resultView = ResultView()
    private val players = Players()

    fun start() {
        val names = Name(inputView.getUserName()).names
        resultView.showDistributedCard(names)
        makePlayer(names)
        resultView.showPlayerCardState(players)
    }

    private fun makePlayer(names: List<String>) {
        names.forEach {
            players.add(it, mutableListOf())
            dealOut(it)
        }
    }

    private fun dealOut(name: String) {
        for (i in 1..2) {
            hit(name)
        }
    }

    private fun hit(name: String) {
        players.updateCard(name, Card())
    }
}
