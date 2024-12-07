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
        val initCardReceivedUsers = gameTable.dealInitCard()

        println()
        resultView.printInitCardReceive(initCardReceivedUsers)
        initCardReceivedUsers.forEach { resultView.printUserCards(user = it, printScore = false) }

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

        println()
        allCardReceivedUsers.forEach { user ->
            resultView.printUserCards(user = user, printScore = true)
        }
    }

    private fun getUsers(): List<User> {
        return inputView.inputNames().map { User.create(name = it) }
    }
}
