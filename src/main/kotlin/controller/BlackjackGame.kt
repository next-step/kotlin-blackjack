package controller

import model.CardVendor
import model.Names
import model.Player
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
        giveCard()
        giveExtraCard()
        printPlayerCard()
    }

    private fun inputNames(): Names {
        return Names(inputView.getUserName())
    }

    private fun createPlayer(names: Names) {
        resultView.showDistributedCard(names)
        names.values.forEach {
            players.add(Player(it))
        }
    }

    private fun giveCard() {
        cardVendor.giveCardToPlayer(players)
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
