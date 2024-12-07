package blackjack.controller

import blackjack.domain.Deck
import blackjack.domain.GameTable
import blackjack.domain.User
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackjackGame(
    private val gameTable: GameTable,
    private val inputView: InputView,
    private val resultView: ResultView,
) {
    fun start() {
        val names = inputView.inputNames()
        val deck = Deck.create()
        val users = names.map { User.create(name = it) }

        val initCardReceivedUsers = gameTable.dealInitCard(users, deck)

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
                        currentUser = currentUser.receiveCard(deck.draw())
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
}
