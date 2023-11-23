package step2.blackjack.controller

import step2.blackjack.domain.model.Dealer
import step2.blackjack.domain.model.Gambler
import step2.blackjack.domain.model.Gamblers
import step2.blackjack.domain.model.Score
import step2.blackjack.view.InputView
import step2.blackjack.view.ResultView

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
        while (gambler.cards.min().toInt() < Score.from(BLACKJACK_NUMBER).toInt() && InputView.drawHitAndStay(gambler)
                .isHit()
        ) {
            dealer.handsOut(gambler)
            ResultView.drawGamblerStatus(gambler)
        }
        if (gambler.cards.cards.size == FIRST_DEAL_COUNT) {
            ResultView.drawGamblerStatus(gambler)
        }
    }
}
