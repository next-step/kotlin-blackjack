package blackjack.controller

import blackjack.domain.BlackJackRules.INIT_CARD_DRAW_COUNT
import blackjack.domain.Deck
import blackjack.domain.User
import blackjack.view.InputView
import blackjack.view.ResultView

private const val MORE_RECEIVE_CARD = "y"
private const val STOP_RECEIVE_CARD = "n"

fun main() {
    val names = InputView.inputNames()
    val deck = Deck.create()
    val users = names.map { User.create(name = it) }
    val initCardReceivedUsers = receiveInitCard(users, deck)

    ResultView.printInitCardReceive(users)
    initCardReceivedUsers.forEach { user -> ResultView.printUserCards(user = user, printScore = false) }

    val allCardReceivedUsers = initCardReceivedUsers.map { user -> receiveCardOrStop(user, deck) }

    allCardReceivedUsers.forEach { user ->
        ResultView.printUserCards(user = user, printScore = true)
    }
}

private fun receiveCardOrStop(
    user: User,
    deck: Deck,
): User {
    var currentUser = user
    while (true) {
        ResultView.printAskReceiveMoreCard(currentUser)
        if (!currentUser.canReceiveCard()) {
            ResultView.printCanNotReceivedCard()
            break
        }
        val answer = InputView.inputReceiveMoreCard()
        when (answer) {
            MORE_RECEIVE_CARD -> {
                currentUser = currentUser.receiveCard(deck.draw())
                ResultView.printUserCards(user = currentUser, printScore = false)
            }

            STOP_RECEIVE_CARD -> break
            else -> ResultView.printInvalidAnswer()
        }
    }
    return currentUser
}

private fun receiveInitCard(
    users: List<User>,
    deck: Deck,
): List<User> {
    return users.map { user ->
        (1..INIT_CARD_DRAW_COUNT).fold(user) { acc, _ ->
            acc.receiveCard(deck.draw())
        }
    }
}
