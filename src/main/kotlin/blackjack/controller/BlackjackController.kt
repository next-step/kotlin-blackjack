package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.User
import blackjack.io.InputView
import blackjack.io.ResultView

class BlackjackController {

    fun start() {
        val userNames = InputView.getUsers()
        val blackjackGame = BlackjackGame(userNames)
        ResultView.printDecks(blackjackGame.dealer, blackjackGame.users)

        blackjackGame.dealUsers(::checkHit, ResultView::printUserDeck)
        blackjackGame.dealDealer { ResultView.printDealerHit() }

        ResultView.printResults(blackjackGame.getGameResult())
    }

    private fun checkHit(user: User): Boolean {
        return !user.isBust() && InputView.checkHit(user.name)
    }
}
