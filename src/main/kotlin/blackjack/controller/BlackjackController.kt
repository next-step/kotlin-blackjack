package blackjack.controller

import blackjack.domain.model.Dealer
import blackjack.domain.model.Gambler
import blackjack.domain.model.Gamblers
import blackjack.view.InputView
import blackjack.view.ResultView

object BlackjackController {

    private const val FIRST_DEAL_COUNT = 2
    private const val BLACKJACK_NUMBER = 21

    fun playBlackJack() {
        val names = InputView.drawInputNamesView()
        val gamblers = Gamblers.from(names)

        val dealer = Dealer.hire()

        repeat(FIRST_DEAL_COUNT) {
            dealer.handsOutAll(gamblers)
        }

        ResultView.drawDealCardDescription(gamblers, FIRST_DEAL_COUNT)
        ResultView.drawGamblerStatus(gamblers)


        gamblers.forEach { gambler ->
            choiceHitAndStay(dealer, gambler)
        }

        ResultView.drawGamblersStatusResult(gamblers, BLACKJACK_NUMBER)
    }

    private fun choiceHitAndStay(dealer: Dealer, gambler: Gambler) {
        while (gambler.shouldDraw(BLACKJACK_NUMBER) && InputView.drawHitAndStay(gambler).isHit()) {
            dealer.handsOut(gambler)
            ResultView.drawGamblerStatus(gambler)
        }
        if (gambler.cards.cards.size == FIRST_DEAL_COUNT) {
            ResultView.drawGamblerStatus(gambler)
        }
    }
}
