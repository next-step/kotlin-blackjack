package controller

import model.Card
import model.CardNumberCalculator
import model.Name
import model.Players
import view.InputView
import view.ResultView

class BlackjackGame {
    private val inputView = InputView()
    private val resultView = ResultView()
    private val players = Players()
    private val cardNumberCalculator = CardNumberCalculator()

    fun start() {
        val names = Name(inputView.getUserName()).names
        resultView.showDistributedCard(names)

        makePlayer(names)
        resultView.showPlayerCardState(players.get())

        checkExtraCard()
        printPlayerCard()
    }

    private fun makePlayer(names: List<String>) {
        names.forEach {
            players.add(it, mutableListOf())
            dealOut(it)
        }
    }

    private fun dealOut(name: String) {
        for (i in DEAL_OUT) {
            hit(name)
        }
    }

    private fun hit(name: String) {
        players.updateCard(name, Card.generate())
    }

    private fun checkExtraCard() {
        val usersUnderTwentyTwo = players.get().filter {
            cardNumberCalculator.isUnderTwentyOne(it.value)
        }

        usersUnderTwentyTwo.forEach {
            if (isNeedToExtraCard(it.key)) {
                hit(it.key)
                resultView.showSpecificUserCardState(it.key, it.value)
            }
        }
    }

    private fun isNeedToExtraCard(name: String): Boolean {
        var inputValue = ""
        while (inputValue != YES && inputValue != NO) {
            inputValue = inputView.getExtraCard(name)
        }
        return inputValue == YES
    }

    private fun printPlayerCard() {
        players.get().forEach {
            resultView.showPlayerCardStateResult(it.key, it.value, cardNumberCalculator.totalNumber(it.value))
        }
    }

    companion object {
        private val DEAL_OUT = 0..1
        private const val YES = "y"
        private const val NO = "n"
    }
}
