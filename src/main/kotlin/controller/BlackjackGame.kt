package controller

import model.Card
import model.CardNumber
import model.CardShape
import model.Name
import view.InputView
import view.ResultView

class BlackjackGame {
    private val inputView = InputView()
    private val resultView = ResultView()
    private val card = Card()

    fun start() {
        println("${CardNumber.convertToString(card.cardNumber)}${CardShape.convertToString(card.cardShape)}")
        resultView.showDistributedCard(Name(inputView.getUserName()).names)
    }
}
