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
        ResultView.printUsersDeck(users)
        dealCards(users)
        ResultView.printUsersResult(users)
    }

    private fun dealCards(users: Users) {
        for (user in users) {
            checkHit(user)
        }
    }

    private fun checkHit(user: User) {
        while (!user.isBust() && InputView.checkHit(user.name)) {
            blackjackGame.addCardTo(user)
            ResultView.printUserDeck(user)
        }
        println()
    }
}
