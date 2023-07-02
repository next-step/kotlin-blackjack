package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.user.UserDrawInterface
import blackjack.io.InputView
import blackjack.io.ResultView

class BlackjackController {

    fun start() {
        val userNames = InputView.getUsers()
        val userDrawInterface = UserDrawInterface { user -> InputView.checkHit(user.name) }
        val blackjackGame = BlackjackGame(userNames, userDrawInterface = userDrawInterface)
        ResultView.printCards(blackjackGame.dealer, blackjackGame.users)

        blackjackGame.dealUsers(ResultView::printPlayerCards)
        blackjackGame.dealDealer { ResultView.printDealerHit() }

        ResultView.printResults(blackjackGame.getGameResult())
    }
}
