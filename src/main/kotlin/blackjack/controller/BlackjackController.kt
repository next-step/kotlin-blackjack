package blackjack.controller

import blackjack.domain.model.player.Dealer
import blackjack.domain.model.Deck
import blackjack.domain.model.player.Gambler
import blackjack.domain.model.Gamblers
import blackjack.view.InputView
import blackjack.view.ResultView

object BlackjackController {

    private const val FIRST_DEAL_COUNT = 2

    fun playBlackJack() {
        val gamblers = Gamblers.from(InputView.drawInputNamesView())
        val dealer = Dealer.of()
        val deck: Deck = Deck.all()

        dealFirst(deck, dealer, gamblers)

        ResultView.drawFirstDealCard(dealer, gamblers, FIRST_DEAL_COUNT)

        hitGamblers(deck, gamblers)
        hitDealer(deck, dealer)

        ResultView.drawGameResult(dealer, gamblers)
    }

    private fun dealFirst(deck: Deck, dealer: Dealer, gamblers: Gamblers) {
        repeat(FIRST_DEAL_COUNT) {
            deck.peek(dealer)
            deck.peek(gamblers)
        }
    }

    private fun hitGamblers(deck: Deck, gamblers: Gamblers) {
        gamblers.forEach { gambler ->
            hitGambler(deck, gambler)
        }
    }
    private fun hitGambler(deck: Deck, gambler: Gambler) {
        while (gambler.shouldDraw() && InputView.drawHitAndStay(gambler).isHit()) {
            deck.peek(gambler)
            ResultView.drawGamblerStatus(gambler)
        }
    }

    private fun hitDealer(deck: Deck, dealer: Dealer) {
        while (dealer.shouldDraw()) {
            ResultView.drawHitDealer(dealer)
            deck.peek(dealer)
        }
    }
}
