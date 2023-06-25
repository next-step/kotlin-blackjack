package blackjack.controller

import blackjack.utils.StringUtils
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackJackController {

    fun play() {

        val inputPlayers = InputView.inputPlayers()
        ResultView.printEnter()
        val players = StringUtils.replaceWhiteSpaceAndSplitByComma(inputPlayers)
    }
}
