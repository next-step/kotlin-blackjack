package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.UserDrawInterface
import blackjack.io.InputView
import blackjack.io.ResultView

class BlackjackController {

    fun start() {
        val userNames = InputView.getUsers()
        val userDrawInterface = UserDrawInterface { user -> InputView.checkHit(user.name) }
        val blackjackGame = BlackjackGame(userNames, userDrawInterface = userDrawInterface)
        ResultView.printDecks(blackjackGame.dealer, blackjackGame.users)

        blackjackGame.dealUsers(ResultView::printPlayerDeck)
        blackjackGame.dealDealer { ResultView.printDealerHit() }

        ResultView.printResults(blackjackGame.getGameResult())
    }
}
