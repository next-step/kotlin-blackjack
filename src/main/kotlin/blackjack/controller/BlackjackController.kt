package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.User
import blackjack.io.InputView
import blackjack.io.ResultView

class BlackjackController {

    fun start() {
        val userNames = InputView.getUsers()
        val blackjackGame = BlackjackGame(userNames)
        ResultView.printDecks(blackjackGame.getDealerDeck(), blackjackGame.users)

        blackjackGame.dealUsers(::checkHit, ResultView::printUserDeck)
        blackjackGame.dealDealer { ResultView.printDealerHit() }

        ResultView.printResult(blackjackGame.getDealerDeck(), blackjackGame.users)
    }

    private fun checkHit(user: User): Boolean {
        return !user.isBust() && InputView.checkHit(user.name)
    }
}
