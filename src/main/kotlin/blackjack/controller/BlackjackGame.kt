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
        val users = playGame(gameTable)
        printGameResult(users)
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
        resultView.linebreak()
        resultView.printInitCardReceive(initCardReceivedUsers)
        resultView.printUsersCard(users = initCardReceivedUsers, printScore = false)
        return initCardReceivedUsers
    }

    private fun processTurn(users: List<User>): List<User> {
        return users.map { turn(it) }
    }

    private fun turn(user: User): User {
        return if (user.canReceiveCard() && inputView.inputReceiveMoreCard(user)) {
            val updatedUser = user.receiveCard(Deck.create().draw())
            resultView.printUserCard(user = updatedUser, printScore = false)
            turn(updatedUser)
        } else {
            user
        }
    }

    private fun printGameResult(users: List<User>) {
        resultView.linebreak()
        resultView.printUsersCard(users = users, printScore = true)
    }
}
