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
        return if (user.canHit() && inputView.inputHit(user)) {
            val hitUser = user.hit(Deck.create().draw())
            resultView.printUserCard(user = hitUser, printScore = false)
            turn(hitUser)
        } else {
            user
        }
    }

    private fun printGameResult(users: List<User>) {
        resultView.linebreak()
        resultView.printUsersCard(users = users, printScore = true)
    }
}
