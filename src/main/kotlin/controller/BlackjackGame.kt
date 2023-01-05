package controller

import model.CardNumberCalculator
import model.CardVendor
import model.Name
import model.Players
import view.InputView
import view.ResultView

class BlackjackGame(private val players: Players, private val cardNumberCalculator: CardNumberCalculator, private val cardVendor: CardVendor) {
    private val inputView = InputView()
    private val resultView = ResultView()

    fun start() {
        val names = Name(inputView.getUserName()).names
        resultView.showDistributedCard(names)
        players.generate(names)

        cardVendor.giveCardToPlayer(players, names)
        resultView.showPlayerCardState(players.get())

        cardVendor.checkExtraCard(players, cardNumberCalculator)

        printPlayerCard()
    }

    private fun printPlayerCard() {
        players.get().forEach {
            resultView.showPlayerCardStateResult(it.key, it.value, cardNumberCalculator.totalNumber(it.value))
        }
    }
}
