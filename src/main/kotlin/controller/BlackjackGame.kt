package controller

import model.Name
import view.InputView
import view.ResultView

class BlackjackGame {
    private val inputView = InputView()
    private val resultView = ResultView()
    fun start() {
        resultView.showDistributedCard(Name(inputView.getUserName()).names)
    }
}
