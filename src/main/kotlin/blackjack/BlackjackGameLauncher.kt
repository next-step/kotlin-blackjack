package blackjack

import blackjack.domain.Dealer
import blackjack.domain.DealerCardDeck
import blackjack.domain.Player
import blackjack.service.ShuffledDeckCardsBuilder
import blackjack.view.input.InputView
import blackjack.view.result.ResultView

class BlackjackGameLauncher(private val inputView: InputView, private val resultView: ResultView) {
    fun launch() {
        val players = inputView.getPlayers()

        val dealer = Dealer(DealerCardDeck(ShuffledDeckCardsBuilder().build()))
        dealer.deliverBasicCards(players)
        resultView.showDeliveredBasicCards(players)

        players.items.forEach { player -> deliverAdditionalCards(dealer, player) }

        resultView.showPlayerResults(players)
    }

    private fun deliverAdditionalCards(dealer: Dealer, player: Player) {
        while (player.cardsHandler.canReceiveAdditionalCard() && inputView.askToReceiveAdditionalCardOrNot(player)) {
            dealer.deliverCard(player)
            resultView.showPlayerCards(player, true)
        }
    }
}
