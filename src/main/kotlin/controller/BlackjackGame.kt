package controller

import model.Dealer
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
        createDealer()
        createPlayer(names)
        giveCard(names)
        giveExtraCard()
        printPlayerCard()
        decideGameResult()
        printGameResult()
    }

    private fun inputNames(): Names {
        return Names(inputView.getUserName())
    }

    private fun createDealer() {
        players.add(Dealer())
    }

    private fun createPlayer(names: Names) {
        names.values.forEach {
            players.add(Player(it))
        }
    }

    private fun giveCard(names: Names) {
        resultView.showDistributedCard(names)
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
        resultView.showPlayerCardStateResult(players.get())
    }

    private fun decideGameResult() {
        GameResultReader().decideResult(players.get())
    }

    private fun printGameResult() {
        resultView.showGameResult(players.get())
    }
}
