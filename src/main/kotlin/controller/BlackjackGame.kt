package controller

import model.Name
import view.InputView

class BlackjackGame {
    private val inputView = InputView()

    fun start() {
        Name(inputView.getUserName())
    }
}
