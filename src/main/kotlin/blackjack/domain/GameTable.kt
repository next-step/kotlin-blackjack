package blackjack.domain

import blackjack.domain.BlackJackRules.INIT_CARD_DRAW_COUNT

object GameTable {
    fun dealInitCard(
        users: List<User>,
        deck: Deck,
    ): List<User> {
        return users.map { user ->
            (1..INIT_CARD_DRAW_COUNT).fold(user) { acc, _ ->
                acc.receiveCard(deck.draw())
            }
        }
    }
}

// fun main() {
//    val names = InputView.inputNames()
//    val deck = Deck.create()
//    val users = names.map { User.create(name = it) }
//    val initCardReceivedUsers = receiveInitCard(users, deck)
//
//    ResultView.printInitCardReceive(users)
//    initCardReceivedUsers.forEach { user -> ResultView.printUserCards(user = user, printScore = false) }
//
//    val allCardReceivedUsers = initCardReceivedUsers.map { user -> receiveCardOrStop(user, deck) }
//
//    allCardReceivedUsers.forEach { user ->
//        ResultView.printUserCards(user = user, printScore = true)
//    }
// }
//
// private fun receiveCardOrStop(
//    user: User,
//    deck: Deck,
// ): User {
//    var currentUser = user
//    while (true) {
//        ResultView.printAskReceiveMoreCard(currentUser)
//        if (!currentUser.canReceiveCard()) {
//            ResultView.printCanNotReceivedCard()
//            break
//        }
//        val answer = InputView.inputReceiveMoreCard()
//        when (answer) {
//            MORE_RECEIVE_CARD -> {
//                currentUser = currentUser.receiveCard(deck.draw())
//                ResultView.printUserCards(user = currentUser, printScore = false)
//            }
//
//            STOP_RECEIVE_CARD -> break
//            else -> ResultView.printInvalidAnswer()
//        }
//    }
//    return currentUser
// }
//
// fun receiveInitCard(
//    users: List<User>,
//    deck: Deck,
// ): List<User> {
//    return users.map { user ->
//        (1..INIT_CARD_DRAW_COUNT).fold(user) { acc, _ ->
//            acc.receiveCard(deck.draw())
//        }
//    }
// }
