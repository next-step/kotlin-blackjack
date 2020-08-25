package blackjack

import blackjack.domain.game.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.view.InputView
import blackjack.view.ResultView

class GameHost(private val players: Players) {
    private val dealer = Dealer()

    fun start() {
        initPlayers()
        for (player in players.participants) {
            requestMoreAction(player, dealer)
        }

        ResultView.printDealerResult(dealer, dealer.fillHand())
        ResultView.printPlayers(players)
        ResultView.printWinners(players.findWinners())
    }

    private fun initPlayers() {
        dealer.initPlayersHand(players)
        ResultView.printGiveCard(players)
        ResultView.printDealerInitialCard(dealer)
        ResultView.printPlayers(players)
    }

    private fun requestModeCard(player: Player) = InputView.readMoreCard(player) == InputView.YES

    private fun requestMoreAction(player: Player, dealer: Dealer) {
        while (player.hasFreeSpace() && requestModeCard(player)) {
            val handStatus = dealer.giveCardTo(player)
            ResultView.printPlayer(player, handStatus)
        }
    }
}
