package blackjack

import blackjack.domain.CardDeck
import blackjack.domain.Dealer
import blackjack.view.input.InputView
import blackjack.view.result.ResultView

class BlackjackGameLauncher(private val inputView: InputView, private val resultView: ResultView) {
    fun launch() {
        val players = inputView.getPlayers()

        val dealer = Dealer(CardDeck(CardDeck.getShuffledCards()))
        dealer.deliverBasicCards(players)
        resultView.showDeliveredBasicCards(players)

        dealer.deliverAdditionalCards(players, inputView, resultView)
        resultView.showPlayerResults(players)
    }
}
