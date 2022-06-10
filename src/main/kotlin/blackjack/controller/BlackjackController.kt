package blackjack.controller

import blackjack.domain.User
import blackjack.domain.Users
import blackjack.view.InputView
import blackjack.view.OutputView

/**
 * 블랙잭 진행을 위한 컨트롤러
 * Created by Jaesungchi on 2022.06.07..
 */
object BlackjackController {
    fun startGame() {
        val players = Users.of(InputView.getPlayersName())
        OutputView.printHandOutMessage(players)
        OutputView.printUsersCard(players)

        // 카드 받는 부분.
        players.users.forEach { user ->
            hitStage(user)
        }

        OutputView.printResult(players)
    }

    private fun hitStage(user: User) {
        while (!user.cards.isOverScore()) {
            OutputView.printMoreCard(user)
            if (!InputView.getYesOrNo()) return
            user.cards.addCard()
            OutputView.printUserCard(user)
        }
    }
}
