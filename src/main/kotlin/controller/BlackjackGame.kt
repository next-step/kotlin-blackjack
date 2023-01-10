package controller

import model.CardVendor
import model.Names
import model.Players
import view.InputView
import view.ResultView

class BlackjackGame(
    private val players: Players,
    private val cardVendor: CardVendor,
    private val inputView: InputView,
    private val resultView: ResultView
) {
    fun start() {
        val names = inputNames()
        createPlayer(names)
        giveCard(names)
        giveExtraCard()
        printPlayerCard()
    }

    private fun inputNames(): Names {
        return Names(inputView.getUserName())
    }

    private fun createPlayer(names: Names) {
        resultView.showDistributedCard(names)
        players.generate(names)
    }

    private fun giveCard(names: Names) {
        cardVendor.giveCardToPlayer(players, names)
        resultView.showPlayerCardState(players.get())
    }

    private fun giveExtraCard() {
        cardVendor.giveExtraCard(
            players,
            { name ->
                inputView.getExtraCard(name)
            },
            { name, cards ->
                resultView.showSpecificUserCardState(name, cards)
            }
        )
    }

    private fun printPlayerCard() {
        resultView.showPlayerCardStateResult(players.get(), cardVendor)
    }
}
