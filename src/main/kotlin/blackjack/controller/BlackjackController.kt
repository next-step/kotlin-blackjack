package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.user.UserDrawChecker
import blackjack.io.InputView
import blackjack.io.ResultView

class BlackjackController {

    fun start() {
        val userNames = InputView.getUsers()
        val userDrawChecker = UserDrawChecker { user -> InputView.checkHit(user.name) }
        val blackjackGame = BlackjackGame(userNames, userDrawChecker, InputView::getBetMoney)
        ResultView.printCards(blackjackGame.dealer, blackjackGame.users)

        blackjackGame.dealUsers(ResultView::printPlayerCards)
        blackjackGame.dealDealer { ResultView.printDealerHit() }

        ResultView.printResults(blackjackGame.getGameResult())
    }
}
