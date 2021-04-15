package blackjack

import blackjack.card.CardFactory
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackGame {

    private val dealer = Dealer(CardFactory.makeCards())

    fun start() {
        val players: List<Player> = InputView.inputPlayerNames().map { Player(it) }
        players.forEach { it.receiveCards(dealer.giveTwoCards()) }
        dealer.receiveCards(dealer.giveTwoCards())

        OutputView.showPlayersCard(players, dealer)

        players.forEach(::needToMoreCard)
        needToMoreDealerCard(dealer)

        OutputView.showResult(players.plus(dealer))

        OutputView.showResultWinAndLose(BlackjackRule.resultWinnerAndLosers(players.plus(dealer)))
    }

    private fun needToMoreCard(player: Player) {
        if (!InputView.inputReceiveCardYn(player.name)) return

        player.receiveCard(dealer.giveCard())
        OutputView.showCard(player)

        if (player.isReceiveMoreCard()) needToMoreCard(player)
    }

    private fun needToMoreDealerCard(dealer: Dealer) {
        if (!dealer.isReceiveMoreCard()) return
        dealer.receiveCard(dealer.giveCard())
        OutputView.showDealerMoreCard(dealer)
    }
}
