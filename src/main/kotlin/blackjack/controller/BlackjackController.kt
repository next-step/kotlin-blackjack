package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.User
import blackjack.domain.Users
import blackjack.io.InputView
import blackjack.io.ResultView

class BlackjackController {
    private val blackjackGame = BlackjackGame()

    fun start() {
        val userNames = InputView.getUsers()
        val userList = userNames.map { name -> User(name, blackjackGame.getInitDeck()) }.toSet()
        val users = Users(userList)
        ResultView.printDecks(blackjackGame.getDealerDeck(), users)
        dealCards(users)
        ResultView.printResult(blackjackGame.getDealerDeck(), users)
    }

    private fun dealCards(users: Users) {
        for (user in users) {
            dealUser(user)
        }
    }

    private fun dealUser(user: User) {
        blackjackGame.userHit(user, ::checkHit) {
            ResultView.printUserDeck(user)
        }
    }

    private fun checkHit(user: User): Boolean {
        return !user.isBust() && InputView.checkHit(user.name)
    }
}
