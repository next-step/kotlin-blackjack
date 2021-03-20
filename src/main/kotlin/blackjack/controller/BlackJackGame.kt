package blackjack.controller

import blackjack.domain.DealAnswer
import blackjack.domain.DealMachine
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.ResultView

object BlackJackGame {

    fun start(players: Players) {
        DealMachine.initialDeal(players)
        ResultView.showInitialPlayerCards(players)
    }

}