package blackjack.controller

import blackjack.domain.card.Deck
import blackjack.domain.user.Users
import blackjack.view.InputView
import blackjack.view.OutputView

/**
 * 블랙잭 진행을 위한 컨트롤러
 * Created by Jaesungchi on 2022.06.07..
 */
object BlackjackController {
    fun startGame() {
        val players = Users.of(InputView.getPlayersName(), Deck())
        OutputView.printHandOutMessage(players)
        OutputView.printUsersCard(players)
        players.hit()
        OutputView.printResult(players)
    }
}
