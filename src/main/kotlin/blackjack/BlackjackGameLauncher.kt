package blackjack

import blackjack.domain.DealerCardDeck
import blackjack.domain.Dealer
import blackjack.view.input.InputView
import blackjack.view.result.ResultView

class BlackjackGameLauncher(private val inputView: InputView, private val resultView: ResultView) {
    fun launch() {
        val players = inputView.getPlayers()

        val dealer = Dealer(DealerCardDeck(DealerCardDeck.getShuffledCards()))
        dealer.deliverBasicCards(players)
        resultView.showDeliveredBasicCards(players)

        dealer.deliverAdditionalCards(players, inputView, resultView)
        resultView.showPlayerResults(players)
    }
}
