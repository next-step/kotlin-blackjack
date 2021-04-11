package blackjack

import blackjack.card.CardFactory
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackGame {

    private val dealer = Dealer(CardFactory.makeCards())

    fun start() {
        val players: List<Player> = InputView.inputPlayerNames().map {
            Player(it, dealer.giveTwoCards())
        }

        OutputView.showPlayersCard(players)

        players.forEach(::needToMoreCard)

        OutputView.showResult(players)
    }

    private fun needToMoreCard(player: Player) {
        if (!InputView.inputReceiveCardYn(player.name)) return

        player.receiveCard(dealer.giveCard())
        OutputView.showCard(player)

        if (player.isReceiveMoreCard()) needToMoreCard(player)
    }
}
