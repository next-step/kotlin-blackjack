package blackjack.controller

import blackjack.domain.model.Dealer
import blackjack.domain.model.Deck
import blackjack.domain.model.Gambler
import blackjack.domain.model.Gamblers
import blackjack.domain.model.Name
import blackjack.view.InputView
import blackjack.view.ResultView

object BlackjackController {

    private const val DEALER_NAME = "딜러"
    private const val DEALER_MAX_HIT_NUMBER = 16
    private const val FIRST_DEAL_COUNT = 2
    private const val BLACKJACK_NUMBER = 21

    fun playBlackJack() {
        val names = InputView.drawInputNamesView()
        val gamblers = Gamblers.from(names)
        val dealer = Dealer.from(Name.from(DEALER_NAME))

        val deck: Deck = Deck.all()

        deck.peek(dealer)
        repeat(FIRST_DEAL_COUNT) {
            deck.peek(gamblers)
        }

        ResultView.drawDealCardDescription(gamblers, FIRST_DEAL_COUNT)
        ResultView.drawDealerStatus(dealer)
        ResultView.drawGamblersStatus(gamblers)

        gamblers.forEach { gambler ->
            choiceHitAndStay(deck, gambler)
        }

        InputView.drawHitDealer(DEALER_MAX_HIT_NUMBER)
        whileHitDealer(deck, dealer)

        ResultView.drawDealerStatusResult(dealer, BLACKJACK_NUMBER)
        ResultView.drawGamblersStatusResult(gamblers, BLACKJACK_NUMBER)

        ResultView.drawVictoryResult()
    }

    private fun choiceHitAndStay(deck: Deck, gambler: Gambler) {
        while (gambler.shouldDraw(BLACKJACK_NUMBER) && InputView.drawHitAndStay(gambler).isHit()) {
            deck.peek(gambler)
            ResultView.drawGamblerStatus(gambler)
        }
        if (gambler.cards.cards.size == FIRST_DEAL_COUNT) {
            ResultView.drawGamblerStatus(gambler)
        }
    }
    private fun whileHitDealer(deck: Deck, dealer: Dealer) {
        while (dealer.shouldDraw(DEALER_MAX_HIT_NUMBER)) {
            deck.peek(dealer)
        }
    }

}
