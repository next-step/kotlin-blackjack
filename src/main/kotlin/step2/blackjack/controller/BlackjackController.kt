package step2.blackjack.controller

import step2.blackjack.model.Dealer
import step2.blackjack.model.Gambler
import step2.blackjack.model.Gamblers
import step2.blackjack.view.InputView
import step2.blackjack.view.ResultView

/**
 * 블랙잭 컨트롤러
 * */
object BlackjackController {

    private const val FIRST_DEAL_COUNT = 2

    fun playBlackJack() {
        val names = InputView.drawInputNamesView()
        val gamblers = Gamblers.from(names)

        val dealer = Dealer.hire()

        repeat(FIRST_DEAL_COUNT) {
            dealer.handsOut(gamblers)
        }
        ResultView.drawDealCardDescription(gamblers, FIRST_DEAL_COUNT)

        ResultView.drawGamblerStatus(gamblers)

//        val choiceDealListener: (Gambler) -> String = { gambler ->
//            InputView.drawInputDrawCard(gambler)
//        }
//
//        gamblers.choiceDealCard(choiceDealListener) { gambler ->
//            ResultView.drawGamblerStatus(gambler)
//        }
//
//        ResultView.drawGamblersStatusResult(gamblers)
    }
}