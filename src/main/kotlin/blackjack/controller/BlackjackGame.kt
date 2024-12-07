package blackjack.controller

import blackjack.domain.Deck
import blackjack.domain.GameTable
import blackjack.domain.User
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackjackGame(
    private val inputView: InputView,
    private val resultView: ResultView,
) {
    fun start() {
        val gameTable = GameTable(getUsers(), Deck.create())
        val allCardReceivedUsers = playGame(gameTable)
        printGameResult(allCardReceivedUsers)
    }

    private fun getUsers(): List<User> {
        return inputView.inputNames().map { User.create(name = it) }
    }

    private fun playGame(gameTable: GameTable): List<User> {
        val initCardReceivedUsers = setUpInitCard(gameTable)
        return processTurn(initCardReceivedUsers)
    }

    private fun setUpInitCard(gameTable: GameTable): List<User> {
        val initCardReceivedUsers = gameTable.dealInitCard()
        resultView.printInitCardReceive(initCardReceivedUsers)
        initCardReceivedUsers.forEach { resultView.printUserCards(user = it, printScore = false) }
        return initCardReceivedUsers
    }

    private fun processTurn(initCardReceivedUsers: List<User>): List<User> {
        val allCardReceivedUsers =
            initCardReceivedUsers.map { user ->
                var currentUser = user
                while (true) {
                    if (!currentUser.canReceiveCard()) {
                        resultView.printCanNotReceivedCard()
                        break
                    }
                    resultView.printAskReceiveMoreCard(currentUser)
                    val moreCard = inputView.inputReceiveMoreCard()
                    if (moreCard) {
                        currentUser = currentUser.receiveCard(Deck.create().draw())
                        resultView.printUserCards(user = currentUser, printScore = false)
                    } else {
                        break
                    }
                }
                currentUser
            }
        return allCardReceivedUsers
    }

    private fun printGameResult(allCardReceivedUsers: List<User>) {
        println()
        allCardReceivedUsers.forEach { user ->
            resultView.printUserCards(user = user, printScore = true)
        }
    }
}
