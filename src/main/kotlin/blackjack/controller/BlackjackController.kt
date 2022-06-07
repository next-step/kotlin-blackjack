package blackjack.controller

import blackjack.domain.Users
import blackjack.view.InputView

/**
 * 블랙잭 진행을 위한 컨트롤러
 * Created by Jaesungchi on 2022.06.07..
 */
object BlackjackController {
    fun playGame() {
        val players = Users.of(InputView.getPlayersName())
    }
}
